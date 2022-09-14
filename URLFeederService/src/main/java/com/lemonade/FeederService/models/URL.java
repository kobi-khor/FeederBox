package com.lemonade.FeederService.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(	name = "url_collection")
public class URL {

    @Id
    private String id;

    private String url;

    @Column(name = "date_created")
    private Timestamp created_date;

    @Column(name = "times_processed")
    private Integer timesProcessed;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "last_processed")
    private Timestamp lastProcessed;


}
