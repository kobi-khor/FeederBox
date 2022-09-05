package com.lemonade.FeederService.repository;

import com.lemonade.FeederService.models.URL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLRepository extends JpaRepository<URL, String> {
}
