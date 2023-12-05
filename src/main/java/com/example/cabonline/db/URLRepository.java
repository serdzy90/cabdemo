package com.example.cabonline.db;

import com.example.cabonline.db.mappings.URLMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URLMapping, Long> {
    Optional<URLMapping> findByHash(String hash);
    Optional<URLMapping> findByLongURL(String longURL);
}
