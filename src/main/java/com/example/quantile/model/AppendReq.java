package com.example.quantile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Data
public class AppendReq {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppendReq.class);

    @JsonProperty("poolId")
    private Long poolId;

    @JsonProperty("poolValues")
    private List<Double> poolValues;

    public void validate() throws IllegalArgumentException{
        if (poolId == null || poolValues == null) {
            LOGGER.warn("AppendReq have poolId or pool values is null");
            throw new IllegalArgumentException("poolId or poolValues is null");
        }
    }

}
