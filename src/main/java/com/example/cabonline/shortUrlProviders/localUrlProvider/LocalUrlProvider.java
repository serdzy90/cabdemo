package com.example.cabonline.shortUrlProviders.localUrlProvider;

import com.example.cabonline.db.URLRepository;
import com.example.cabonline.db.mappings.URLMapping;
import com.example.cabonline.shortUrlProviders.ShortUrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocalUrlProvider extends ShortUrlProvider {
    private final URLRepository urlRepository;
    private final String baseUrl;

    @Autowired
    public LocalUrlProvider(URLRepository urlRepository,
                            @Value("${server.address}") String address,
                            @Value("${server.port}") String port) {
        super();
        this.urlRepository = urlRepository;
        this.baseUrl = "http://" + address + ":" + port;
    }

    @Override
    public String getShortUrl(String longUrl) {
        String hash;
        Optional<URLMapping> urlMappingOptional = urlRepository.findByLongURL(longUrl);

        if (urlMappingOptional.isPresent()) {
            hash = urlMappingOptional.get().getHash();
        } else {
            hash = UUID.randomUUID().toString().substring(0, 8);
            URLMapping urlMapping = new URLMapping(hash, longUrl);
            urlRepository.save(urlMapping);
        }

        return baseUrl + "/r/" + hash;
    }
}
