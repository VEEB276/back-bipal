package org.bipal.external.supabase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "supabaseFunctions", url = "${supabase.base-url}/functions/v1", configuration = SupabaseFunctionsFeignConfig.class)
public interface SupabaseFunctionsClient {

    @PostMapping(value = "/delete-user", consumes = "application/json")
    void deleteUser(@RequestBody Map<String, Object> body);

    @PostMapping(value = "/update-user-metadata", consumes = "application/json")
    void updateUserMetadata(@RequestBody Map<String, Object> body);
}
