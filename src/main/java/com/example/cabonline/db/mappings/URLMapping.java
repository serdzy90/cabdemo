package com.example.cabonline.db.mappings;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "URL_MAPPING")
public class URLMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hash;
    private String longURL;

    public URLMapping() {
    }

    public URLMapping(String hash, String longURL) {
        this.hash = hash;
        this.longURL = longURL;
    }
}
