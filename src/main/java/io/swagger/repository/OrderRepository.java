package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import io.swagger.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    /**
     * Saves a store to the mongo database.
     * PUT/POST Request.
     *
     * @param order body of a Order object to be passed into the database
     * @return the store that was saved
     */
    Order save(Order order);

    /**
     * Find and return a Store object by it's id in the database.
     * GET request.
     *
     * @param id String id of a Order object
     * @return Store object associated with the id passed through the method
     */
    Order findById(String id);

    /**
     * Return all Store objects in the database.
     *
     * @return list of Store objects in the database
     */
    List<Order> findAll();

    /**
     * Deletes a given entity by storeID.
     * DELETE request.
     *
     * @param orderId id to identify a store
     */
    void deleteById(String orderId);

    /**
     * Deletes all stores managed by the repository.
     * DELETE request.
     */
    void deleteAll();

    /**
     * Returns the number of Stores in the database.
     *
     * @return the number of Stores in the database
     */
    long count();

}
