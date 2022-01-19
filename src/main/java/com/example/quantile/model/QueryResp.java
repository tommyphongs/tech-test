package com.example.quantile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryResp {

    @JsonProperty("total_element")
    private int totalElement;

    @JsonProperty("quantileResult")
    private double result;

}
