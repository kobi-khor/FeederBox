package com.lemonade.FeederService.repository;

import com.lemonade.FeederService.models.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, String> {

    URL findByUrl(String url);
}
