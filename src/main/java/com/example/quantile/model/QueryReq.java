package com.example.quantile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class QueryReq {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryReq.class);

    @JsonProperty("poolId")
    private Long poolId;

    @JsonProperty("percentile")
    private Double percentile;

    public void validate() throws IllegalArgumentException {
        if (poolId == null || percentile == null) {
            LOGGER.warn("QueryReq have poolId or pool values is null");
            throw new IllegalArgumentException("poolId or percentile is null");
        }
    }

}
