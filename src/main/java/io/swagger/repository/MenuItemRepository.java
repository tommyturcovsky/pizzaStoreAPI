package io.swagger.repository;

import java.util.List;

import io.swagger.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuItemRepository extends MongoRepository<MenuItem, String> {


    /**
     * Saves a MenuItem instance into the repository.
     * POST operation.
     *
     * @param menuItem the body of a MenuItem instance
     * @return the MenuItem instance with the fields specified in the body
     */
    MenuItem save(MenuItem menuItem);

    /**
     * Lists a MenuItem at a specific store in the repository.
     * GET operation.
     *
     * @return the list of MenuItem at a specific store
     */
    List<MenuItem> findAll();


    /**
     * Get an instance of MenuItem by its ID from the repository.
     * GET operation.
     *
     * @param menuItemId the ID of the menu item
     * @return the MenuItem instance based on the given menu item ID
     */
    MenuItem findById(String menuItemId);


    /**
     * Deletes all of MenuItem instances in the repository
     * DELETE operation.
     */
    void deleteAll();

    /**
     * Deletes a MenuItem instance from the repository by its ID.
     * DELETE operation.
     *
     * @param menuItemID the ID of the menu item to delete
     */
    void deleteById(String menuItemID);

    /**
     * Returns the number of MenuItem instances in the database.
     *
     * @return the number of MenuItem instances in the database
     */
    long count();


}

