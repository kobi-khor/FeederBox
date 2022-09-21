package com.lemonade.HTMLFileWorker.model;

import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class URL {

    private String id;

    private String url;

    private Timestamp created_date;

    private Integer timesProcessed;

    private String contentType;

    private Timestamp lastProcessed;
}