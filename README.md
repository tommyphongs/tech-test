# Quantile service
And service that provide api to:
* Append list of double value corresponding to poolId 
* Calculate the quantile of pool's dataset

## Design
I try to decoupling between controller and Repository, and between Controller 
and Mathematics calculations, in our case is quantiles. If we want to add more calculation, we just create new mathematics 
class and use exists Repository to get data, calculate on that data and response data into user by Controller.
Otherwise, if we want a high availability or  high scalability, we just implement high scalability and availability Repository

We use Map<Value, Num_of_Occurrences>  to reduce resources and improve performance


## How to run
> ./mvnw spring-boot:run

It runs on default port: ```8080```

## How to run test

> ./mvnw clean test

## API 

The api example is place at ```quantile_service.http``` file

### Add api:

```
POST http://localhost:8080/append
Content-Type: application/json

{
"poolId": {pool_id},
"poolValues": {array_values}
}
```

For example:
```
POST http://localhost:8080/append
Content-Type: application/json

{
  "poolId": 1,
  "poolValues": [
    1, 2.0, 3, 4, 5
  ]
}
 
```



### Query api: 

``` 
POST http://localhost:8080/query
Content-Type: application/json
{
  "poolId": {poolId},
  "percentiles": {percentiles}
}
```

For example:
``` 
POST http://localhost:8080/query
Content-Type: application/json

{
  "poolId": 1,
  "percentiles": 90
}
```






