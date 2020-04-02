package com.redhat.labs.omp.models.gitlab;

import javax.json.bind.annotation.JsonbProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Namespace {

    @JsonbProperty("id")
    private Integer id;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("path")
    private String path;
    @JsonbProperty("kind")
    private String kind;
    @JsonbProperty("full_path")
    private String fullPath;
    @JsonbProperty("parent_id")
    private Integer parentId;

}
