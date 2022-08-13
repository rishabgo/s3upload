package com.rishabh.s3upload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Movie {

    private final String movieId;
    private final String movieName;
    private final String description;
}
