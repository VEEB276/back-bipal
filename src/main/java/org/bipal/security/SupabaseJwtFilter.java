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

        String auth = request.getHeader("Authorization");
        if (!protegido) { // no protegido -> continuar
            filterChain.doFilter(request, response);
            return;
        }

        if (auth == null || !auth.startsWith("Bearer ")) {
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

    private void unauthorized(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String body = "{\"error\":\"unauthorized\",\"message\":\"" + mensaje.replace('"',' ') + "\"}";
        response.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Siempre se decide dentro del filtro usando la variable protegido.
        return false;
    }
}

