package org.bipal.external.supabase;

import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Configuración Feign para Supabase Auth REST API.
 * Reenvía el Authorization del usuario automáticamente.
 */
public class SupabaseAuthFeignConfig {

    @Bean
    Logger.Level supabaseAuthFeignLoggerLevel() { return Logger.Level.BASIC; }

    @Bean
    public OkHttpClient okHttp3ClientAuth() { return new OkHttpClient(); }

    @Bean
    public Client feignOkHttpClientAuth(OkHttpClient okHttp3ClientAuth) {
        return new feign.okhttp.OkHttpClient(okHttp3ClientAuth);
    }

    @Bean
    public RequestInterceptor userAuthorizationAuthInterceptor() {
        return template -> {
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