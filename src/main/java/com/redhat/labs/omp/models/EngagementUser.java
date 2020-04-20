package com.redhat.labs.omp.models;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngagementUser {

    @NotBlank
    @JsonbProperty("first_name")
    private String firstName;
    @NotBlank
    @JsonbProperty("last_name")
    private String lastName;
    @NotBlank
    @JsonbProperty("email")
    private String email;
    @NotBlank
    @JsonbProperty("role")
    private String role;

}
