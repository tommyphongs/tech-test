package com.example.quantile.Repository;

import java.util.List;

/**
 * A repository is an abstract of data storage, it's encapsulating storage, retrieval, and search
 * behavior which emulates a collection of objects. It is similar with {@link org.springframework.stereotype.Repository}
 * If we want to our service is igh availability and scalability, we just need implement {@link Repository}
 * interface to work with high availability and high scalability external data storage.
 */
public interface Repository<K, V> {

    /**
     * Append data into one pool, if the pool with id is exists, it appends values to pool, otherwise, it creates
     * new pool with id and append values to this pool
     * @param key: Not null,  poolId
     * @param values: Not null, the values need to append
     * @return is true if we create new pool, otherwise we return false
     */
    boolean append(K key, List<V> values) throws Exception;


    /**
     * Return the dataset of pool with id
     * @param key: Not null, poolId
     * @return values: the pool's dataset, null if id is not in dataset
     */
    List<V> get(K key) throws Exception;

}
