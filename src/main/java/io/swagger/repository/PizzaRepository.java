package io.swagger.repository;

import java.util.List;

import io.swagger.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PizzaRepository extends MongoRepository<Pizza, String> {

    /**
     * Saves a MenuItem instance into the repository.
     * POST operation.
     *
     * @param pizza the body of a MenuItem instance
     * @return the MenuItem instance with the fields specified in the body
     */
    Pizza save(Pizza pizza);

    /**
     * Lists a MenuItems in the repository.
     * GET operation.
     *
     * @return the list of MenuItem at all stores
     */
    List<Pizza> findAll();

    /**
     * Get an instance of MenuItem by its ID from the repository.
     * GET operation.
     *
     * @param pizzaId the ID of the menu item
     * @return the MenuItem instance based on the given menu item ID
     */
    Pizza findById(String pizzaId);

    /**
     * Deletes an instance of MenuItem in the repository
     * DELETE operation.
     */
    //void delete(Pizza pizza);

    /**
     * Deletes all of MenuItem instances in the repository
     * DELETE operation.
     */
    void deleteAll();

    /**
     * Deletes a MenuItem instance from the repository by its ID.
     * DELETE operation.
     *
     * @param pizzaID the ID of the menu item to delete
     */
    void deleteById(String pizzaID);

    /**
     * Returns the number of Pizza instances in the database.
     *
     * @return the number of Pizza instances in the database
     */
    long count();


}
