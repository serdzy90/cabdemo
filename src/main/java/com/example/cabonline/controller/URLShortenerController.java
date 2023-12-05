package com.example.cabonline.controller;

import com.example.cabonline.service.URLShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class URLShortenerController {

    private final URLShortenerService urlShortenerService;

    public URLShortenerController(URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping ("/shorten")
    public ResponseEntity<Map<String, String>> shortenURL(@RequestParam String url, @RequestParam(required = false) String providerName) {
        String shortURL = urlShortenerService.shortenURL(url, providerName);

        Map<String, String> response = new HashMap<>();
        response.put("shortURL", shortURL);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/r/{hash}")
    public RedirectView resolveURL(@PathVariable String hash) throws Exception {
        return urlShortenerService.resolveURL(hash);
    }
}