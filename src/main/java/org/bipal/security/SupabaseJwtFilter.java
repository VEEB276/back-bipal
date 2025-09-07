package org.bipal.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Filtro que valida el JWT emitido por Supabase y bloquea acceso a /api/** si no hay token válido.
 * Extrae numeroDocumento e idPersona desde user_metadata (si existen) y los deja como atributos request.
 */
@Slf4j
@Component
public class SupabaseJwtFilter extends OncePerRequestFilter {

    public static final String ATTR_NUMERO_DOCUMENTO = "numeroDocumentoToken";
    public static final String ATTR_ID_PERSONA = "idPersonaToken";

    @Value("${supabase.jwt-secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        boolean protegido = path.startsWith(request.getContextPath() + "/api/");

        // Permitir siempre preflight CORS sin exigir token
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            auth = request.getHeader("authorization"); // fallback defensivo
        }

        if (!protegido) { // no protegido -> continuar
            filterChain.doFilter(request, response);
            return;
        }

        if (auth == null || !auth.startsWith("Bearer ")) {
            log.warn("Falta token Bearer en request a recurso protegido: {}", path);
            unauthorized(response, "Falta token Bearer");
            return;
        }

        String token = auth.substring(7).trim();
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            // Firma
            if (!signedJWT.verify(new MACVerifier(jwtSecret))) {
                unauthorized(response, "Firma inválida");
                return;
            }
            // Expiración
            Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (exp == null || exp.before(new Date())) {
                unauthorized(response, "Token expirado");
                return;
            }
            // user_metadata
            Object userMetadataObj = signedJWT.getJWTClaimsSet().getClaim("user_metadata");
            if (userMetadataObj instanceof Map<?,?> metadataMap) {
                Object numeroDoc = metadataMap.get("numeroDocumento");
                if (numeroDoc instanceof String numStr && !numStr.isBlank()) {
                    request.setAttribute(ATTR_NUMERO_DOCUMENTO, numStr);
                }
                Object idPersona = metadataMap.get("idPersona");
                if (idPersona instanceof Number n) {
                    request.setAttribute(ATTR_ID_PERSONA, n.longValue());
                } else if (idPersona instanceof String s && !s.isBlank()) {
                    // intentar parse long
                    try { request.setAttribute(ATTR_ID_PERSONA, Long.parseLong(s)); } catch (NumberFormatException ignored) {}
                }
            }
        } catch (ParseException | JOSEException e) {
            log.warn("Error parseando/verificando JWT Supabase: {}", e.getMessage());
            unauthorized(response, "Token inválido");
            return;
        } catch (Exception e) {
            log.error("Error inesperado procesando JWT Supabase", e);
            unauthorized(response, "Error interno token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void addCorsHeaders(HttpServletResponse response) {
        // Asegurar cabeceras CORS en respuestas tempranas (401) para que el navegador no las trate como error de CORS.
        if (!response.containsHeader("Access-Control-Allow-Origin")) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        if (!response.containsHeader("Access-Control-Allow-Headers")) {
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin, X-Requested-With");
        }
        if (!response.containsHeader("Access-Control-Allow-Methods")) {
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        }
    }

    private void unauthorized(HttpServletResponse response, String mensaje) throws IOException {
        addCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String body = "{\"error\":\"unauthorized\",\"message\":\"" + mensaje.replace('"',' ') + "\"}";
        response.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return false; // todo se decide adentro
    }
}
