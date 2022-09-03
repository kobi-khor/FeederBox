package com.lemonade.FeederService.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(	name = "url_collection")
public class URL {

    @Id
    private String id;

    private String url;

    @CreatedDate
    @Column(name = "date_created")
    private Timestamp created_date;



}
