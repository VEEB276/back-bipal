package org.bipal.external.supabase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "supabaseAuth", url = "${supabase.base-url}/auth/v1", configuration = SupabaseAuthFeignConfig.class)
public interface SupabaseAuthClient {

    @PutMapping(value = "/user", consumes = "application/json")
    Map<String, Object> updateUser(@RequestBody Map<String, Object> body);
}