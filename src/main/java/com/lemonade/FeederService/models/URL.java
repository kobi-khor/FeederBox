package com.lemonade.FeederService.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(	name = "url_collection")
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    @Column(name = "date_created")
    private Timestamp timestamp;



}
