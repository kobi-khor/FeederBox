package com.lemonade.FeederService.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("url")
public class URL implements Serializable {

//    @Id
    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "url", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String url;

//    @Column(name = "date_created")
    @Column("created_date")
    private Timestamp created_date;

//    @Column(name = "times_processed")
    @Column("times_processed")
    private Integer timesProcessed;

//    @Column(name = "content_type")
    @Column("content_type")
    private String contentType;

//    @Column(name = "last_processed")
    @Column("last_processed")
    private Timestamp lastProcessed;


}
