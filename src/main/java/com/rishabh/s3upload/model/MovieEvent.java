package com.rishabh.s3upload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class MovieEvent {

    private final String id;
    private final List<Movie> movieList;

}
