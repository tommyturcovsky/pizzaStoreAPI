package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import io.swagger.model.Store;

public interface StoreRepository extends MongoRepository<Store, String> {

    /**
     * Saves a store to the mongo database.
     * PUT/POST Request.
     *
     * @param store body of a Store object to be passed into the database
     * @return the store that was saved
     */
    Store save(Store store);

    /**
     * Find and return a Store object by it's id in the database.
     * GET request.
     *
     * @param id String id of a Store object
     * @return Store object associated with the id passed through the method
     */
    Store findById(String id);

    /**
     * Return all Store objects in the database.
     *
     * @return list of Store objects in the database
     */
    List<Store> findAll();

    /**
     * Deletes a given entity by storeID.
     * DELETE request.
     *
     * @param storeID id to identify a store
     */
    void deleteById(String storeID);

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
