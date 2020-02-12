package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import io.swagger.model.Receipt;

public interface ReceiptRepository extends MongoRepository<Receipt, String> {

    /**
     * Saves a receipt to the mongo database.
     * PUT/POST Request.
     *
     * @param receipt body of a Receipt object to be passed into the database
     * @return the store that was saved
     */
    Receipt save(Receipt receipt);

    /**
     * Find and return a Receipt object by it's id in the database.
     * GET request.
     *
     * @param id String id of a Receipt object
     * @return Store object associated with the id passed through the method
     */
    Receipt findById(String id);

    /**
     * Return all Receipt objects in the database.
     *
     * @return list of Receipt objects in the database
     */
    List<Receipt> findAll();

    /**
     * Deletes a given entity by receiptID.
     * DELETE request.
     *
     * @param receiptID id to identify a store
     */
    void deleteById(String receiptID);

    /**
     * Deletes all receipts managed by the repository.
     * DELETE request.
     */
    void deleteAll();

    /**
     * Returns the number of Receipt objects in the database.
     *
     * @return the number of Receipt objects in the database
     */
    long count();

}
