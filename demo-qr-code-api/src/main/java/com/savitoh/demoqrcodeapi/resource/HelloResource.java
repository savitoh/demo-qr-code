package com.savitoh.demoqrcodeapi.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1")
public class HelloResource {

    private final String corsOrigemAllowed;

    public HelloResource(@Value("${cors.origem.allowed}") String corsOrigemAllowed) {
        this.corsOrigemAllowed = corsOrigemAllowed;
    }

    @GetMapping
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok(String.format("Hello QR-Code API. Cors Origem Allowed: %s", corsOrigemAllowed));
    }
    
}