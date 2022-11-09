package com.lemonade.FeederService.repository;

import com.lemonade.FeederService.models.URL;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends CassandraRepository<URL, String> {

    @AllowFiltering
    Optional<URL> findByUrl(String url);
}