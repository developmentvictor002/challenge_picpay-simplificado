package br.com.victor.picpay_simplificado.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {

    private final RestTemplate restTemplate;
    private static final String AUTH_URL = "https://util.devi.tools/api/v2/authorize";

    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isAuthorized() {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(AUTH_URL, Map.class);

            if(response.getStatusCode().is2xxSuccessful()) {
                Map body = response.getBody();
                if(body != null && body.containsKey("data")) {
                    Map<String, Object> data = (Map<String, Object>) body.get("data");
                    Object auth = data.get("authorization");
                    return Boolean.TRUE.equals(auth);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
