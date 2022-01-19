package com.example.quantile.Repository;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The implementation of {@link Repository} that:
 *  - Inmemory: It means that {@link NonPreserveRepository} just keep data inmemory, don't store to external storage
 *  - Non Preserve insert order of pool's values: For the purpose calculate quantile, we don't need to preserve the insert
 *  order, and we use Map<Value, Num_of_Occurrences> to reduce resources and improve performance
 */
@Configuration
public class NonPreserveRepository<K, V> implements Repository<K, V> {

    private final Map<K, Map<V, Integer>> datasets;

    public NonPreserveRepository() {
        this.datasets = new ConcurrentHashMap<>();
    }

    @Override
    public boolean append(K key, List<V> values) throws Exception {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (values == null) {
            throw new IllegalArgumentException("values is null");
        }

        boolean isNewPool = false;
        if (!datasets.containsKey(key)) {
            isNewPool = true;
            datasets.put(key, new ConcurrentHashMap<>());
        }
        for (V v :  values) {
            if (datasets.get(key).containsKey(v)) {
                datasets.get(key).put(v, datasets.get(key).get(v) + 1);
            }
            else {
                datasets.get(key).put(v, 1);
            }
        }
        return isNewPool;
    }

    @Override
    public List<V> get(K key) throws Exception {
        if (!datasets.containsKey(key)) {
            return null;
        }
        List<V> values = new ArrayList<>();
        datasets.get(key).forEach(((v, count) -> {
            for (int i = 0; i < count; i++) {
                values.add(v);
            }
        } ));
        return values;
    }

}
