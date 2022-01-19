package com.example.quantile;

import com.example.quantile.Repository.NonPreserveRepository;
import com.example.quantile.math.Quantiles;
import com.example.quantile.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class QuantileController {

    private final NonPreserveRepository<Long, Double> repository;

    public QuantileController(NonPreserveRepository<Long, Double> repository) {
        this.repository = repository;
    }

    @PostMapping("/append")
    Object append(@RequestBody AppendReq req) throws Exception {
        req.validate();
        boolean isNewPool = repository.append(req.getPoolId(), req.getPoolValues());
        return isNewPool ? AppendRespEnum.INSERTED : AppendRespEnum.APPENDED;
    }

    @PostMapping("/query")
    Object query(@RequestBody QueryReq req) throws Exception {
        req.validate();
        List<Double> values = repository.get(req.getPoolId());
        if (values == null) {
            return Map.of("Error", String.format("Pool with id %s is not exists", req.getPoolId()));
        }
        double[] arr = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            arr[i] = values.get(i);
        }
        double calculated = Quantiles.calculate(arr, req.getPercentile());
        QueryResp resp = new QueryResp();
        resp.setTotalElement(values.size());
        resp.setResult(calculated);
        return resp;
    }


}
