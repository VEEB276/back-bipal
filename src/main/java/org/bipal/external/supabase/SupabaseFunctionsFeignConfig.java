package org.bipal.external.supabase;

import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.beans.factory.annotation.Value;

/**
 * Configuración Feign para edge functions de Supabase.
 * No añade service-role; reenvía el Authorization del usuario automáticamente.
 */
public class SupabaseFunctionsFeignConfig {

    @Value("${supabase.anon-key:}")
    private String supabaseAnonKey;

    @Bean
    Logger.Level supabaseFunctionsFeignLoggerLevel() { return Logger.Level.BASIC; }

    @Bean
    public OkHttpClient okHttp3ClientFunctions() { return new OkHttpClient(); }

    @Bean
    public Client feignOkHttpClientFunctions(OkHttpClient okHttp3ClientFunctions) {
        return new feign.okhttp.OkHttpClient(okHttp3ClientFunctions);
    }

    @Bean
    public RequestInterceptor userAuthorizationRelayInterceptor() {
        return template -> {
            // Siempre enviar apikey requerida por Supabase Functions (en la pasarela)
            if (supabaseAnonKey != null && !supabaseAnonKey.isEmpty()) {
                template.header("apikey", supabaseAnonKey);
            }
            // Evitar sobreescribir si ya fue seteado manualmente
            if (template.headers().containsKey("Authorization")) return;
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs == null) return; // fuera de contexto HTTP
            HttpServletRequest req = attrs.getRequest();
            String auth = req.getHeader("Authorization");
            if (auth != null && auth.startsWith("Bearer ")) {
                template.header("Authorization", auth);
            }
        };
    }
}
