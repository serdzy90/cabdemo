package com.example.cabonline.shortUrlProviders;

import org.springframework.stereotype.Service;

@Service
public abstract class ShortUrlProvider {
    public abstract String getShortUrl(String longUrl);
}
