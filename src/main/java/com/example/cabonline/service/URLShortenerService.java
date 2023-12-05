package com.example.cabonline.service;

import com.example.cabonline.db.URLRepository;
import com.example.cabonline.db.mappings.URLMapping;
import com.example.cabonline.shortUrlProviders.ShortUrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.Optional;

@Service
public class URLShortenerService {
    private final String DEFAULT_PROVIDER_NAME = "localUrlProvider";
    private final String FAULTY_REDIRECT_URL = "https://www.google.lv";
    private final URLRepository urlRepository;
    private final Map<String,ShortUrlProvider> providers;

    @Autowired
    public URLShortenerService(URLRepository urlRepository, Map<String,ShortUrlProvider> providers) {
        this.urlRepository = urlRepository;
        this.providers = providers;
    }

    public String shortenURL(String longURL, String providerName) {
        ShortUrlProvider urlProvider;
        if (providerName == null) {
            providerName = DEFAULT_PROVIDER_NAME;
        }
        urlProvider = providers.get(providerName);
        return urlProvider.getShortUrl(longURL);
    }

    public RedirectView resolveURL(String hash) {
        System.out.println("resolveURL:"+hash);
        Optional<URLMapping> urlMappingOptional = urlRepository.findByHash(hash);
        if (urlMappingOptional.isPresent()) {
            String longURL = urlMappingOptional.get().getLongURL();
            return new RedirectView(longURL);
        } else {
            return new RedirectView(FAULTY_REDIRECT_URL);
        }
    }
}
