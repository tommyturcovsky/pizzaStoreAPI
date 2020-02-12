package io.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import java.util.ArrayList;
import java.util.List;
import io.swagger.model.MenuItem;
import io.swagger.model.MenuItemType;
import io.swagger.model.Store;
import io.swagger.repository.MenuItemRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.StoreRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories
@ComponentScan(basePackages = {"io.swagger", "io.swagger.api", "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... arg0) throws Exception {
        //Clear Repositories for clean slate
        clearAllRepositories();

        // Fill Repositories with actual Entity values
        putStoreWithMenuEntitiesInMongoDB();

        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }


    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;


        @Override
        public int getExitCode() {
            return 10;
        }

    }


    // Methods to enter data into DB
    // Create new objects and call save()
    // Call in the repository (initialize the repositories and call the methods in the interface)
    public void putStoreWithMenuEntitiesInMongoDB() {
        List<MenuItem> itemsForMainStore = new ArrayList<>();
        List<MenuItem> itemsForSisterStore1 = new ArrayList<>();
        List<MenuItem> itemsForSisterStore2 = new ArrayList<>();


        //Gluten Items
        MenuItem pepperoni = new MenuItem("Pepperoni", 2.00, 80, false, MenuItemType.TOPPING);
        MenuItem olives = new MenuItem("Olives", 2.00, 40, false, MenuItemType.TOPPING);
        MenuItem onions = new MenuItem("Onions", 2.00, 40, false, MenuItemType.TOPPING);
        MenuItem greenPeppers = new MenuItem("Green Peppers", 2.00, 40, false, MenuItemType.TOPPING);
        MenuItem sausage = new MenuItem("Sausage", 2.00, 80, false, MenuItemType.TOPPING);
        MenuItem mushrooms = new MenuItem("Mushrooms", 2.00, 40, false, MenuItemType.TOPPING);
        MenuItem shrimp = new MenuItem("Shrimp", 2.00, 70, false, MenuItemType.TOPPING);
        MenuItem ham = new MenuItem("Ham", 2.00, 80, false, MenuItemType.TOPPING);
        MenuItem pineapple = new MenuItem("Pineapple", 2.00, 50, false, MenuItemType.TOPPING);
        MenuItem cheese = new MenuItem("Cheese", 0.00, 80, false, MenuItemType.CHEESE);
        MenuItem sauce = new MenuItem("Sauce", 2.00, 80, false, MenuItemType.SAUCE);
        MenuItem crustSmall = new MenuItem("Small Crust", 8.00, 100, false, MenuItemType.CRUST_S);
        MenuItem crustMedium = new MenuItem("Medium Crust", 10.00, 200, false, MenuItemType.CRUST_M);
        MenuItem crustLarge = new MenuItem("Large Crust", 12.00, 300, false, MenuItemType.CRUST_L);
        menuItemRepository.save(pepperoni);
        menuItemRepository.save(olives);
        menuItemRepository.save(onions);
        menuItemRepository.save(greenPeppers);
        menuItemRepository.save(sausage);
        menuItemRepository.save(mushrooms);
        menuItemRepository.save(shrimp);
        menuItemRepository.save(ham);
        menuItemRepository.save(pineapple);
        menuItemRepository.save(cheese);
        menuItemRepository.save(sauce);
        menuItemRepository.save(crustSmall);
        menuItemRepository.save(crustMedium);
        menuItemRepository.save(crustLarge);
        itemsForMainStore.add(crustLarge);
        itemsForMainStore.add(crustMedium);
        itemsForMainStore.add(crustSmall);
        itemsForMainStore.add(sauce);
        itemsForMainStore.add(cheese);
        itemsForMainStore.add(pineapple);
        itemsForMainStore.add(ham);
        itemsForMainStore.add(shrimp);
        itemsForMainStore.add(mushrooms);
        itemsForMainStore.add(sausage);
        itemsForMainStore.add(greenPeppers);
        itemsForMainStore.add(onions);
        itemsForMainStore.add(olives);
        itemsForMainStore.add(pepperoni);

        //Also add the gluten options to the glutenFree stores
        for (MenuItem item : itemsForMainStore) {
            itemsForSisterStore1.add(item);
            itemsForSisterStore2.add(item);
        }

        MenuItem pepperoniGF = new MenuItem("Pepperoni GF", 2.00, 70, true, MenuItemType.TOPPING);
        MenuItem olivesGF = new MenuItem("Olives GF", 2.00, 30, true, MenuItemType.TOPPING);
        MenuItem onionsGF = new MenuItem("Onions GF", 2.00, 30, true, MenuItemType.TOPPING);
        MenuItem greenPeppersGF = new MenuItem("Green Peppers GF", 2.00, 30, true, MenuItemType.TOPPING);
        MenuItem sausageGF = new MenuItem("Sausage GF", 2.00, 60, true, MenuItemType.TOPPING);
        MenuItem mushroomsGF = new MenuItem("Mushrooms GF", 2.00, 30, true, MenuItemType.TOPPING);
        MenuItem shrimpGF = new MenuItem("Shrimp GF", 2.00, 60, true, MenuItemType.TOPPING);
        MenuItem hamGF = new MenuItem("Ham GF", 2.00, 80, true, MenuItemType.TOPPING);
        MenuItem pineappleGF = new MenuItem("Pineapple GF", 2.00, 40, true, MenuItemType.TOPPING);
        MenuItem cheeseGF = new MenuItem("Cheese GF", 0.00, 70, true, MenuItemType.CHEESE);
        MenuItem sauceGF = new MenuItem("Ham GF", 0.00, 80, true, MenuItemType.SAUCE);
        MenuItem crustSmallGF = new MenuItem("Small Crust GF", 8.00, 80, true, MenuItemType.CRUST_S);
        MenuItem crustMediumGF = new MenuItem("Medium Crust GF", 10.00, 150, true, MenuItemType.CRUST_M);
        MenuItem crustLargeGF = new MenuItem("Small Crust GF", 12.00, 280, true, MenuItemType.CRUST_L);
        menuItemRepository.save(pepperoniGF);
        menuItemRepository.save(olivesGF);
        menuItemRepository.save(onionsGF);
        menuItemRepository.save(greenPeppersGF);
        menuItemRepository.save(sausageGF);
        menuItemRepository.save(mushroomsGF);
        menuItemRepository.save(shrimpGF);
        menuItemRepository.save(hamGF);
        menuItemRepository.save(pineappleGF);
        menuItemRepository.save(cheeseGF);
        menuItemRepository.save(sauceGF);
        menuItemRepository.save(crustSmallGF);
        menuItemRepository.save(crustMediumGF);
        menuItemRepository.save(crustLargeGF);
        itemsForSisterStore1.add(crustLargeGF);
        itemsForSisterStore1.add(crustMediumGF);
        itemsForSisterStore1.add(crustSmallGF);
        itemsForSisterStore1.add(sauceGF);
        itemsForSisterStore1.add(cheeseGF);
        itemsForSisterStore1.add(pineappleGF);
        itemsForSisterStore1.add(hamGF);
        itemsForSisterStore1.add(shrimpGF);
        itemsForSisterStore1.add(mushroomsGF);
        itemsForSisterStore1.add(sausageGF);
        itemsForSisterStore1.add(greenPeppersGF);
        itemsForSisterStore1.add(onionsGF);
        itemsForSisterStore1.add(olivesGF);
        itemsForSisterStore1.add(pepperoniGF);
        // Add them to store 2 as well
        itemsForSisterStore2.add(crustLargeGF);
        itemsForSisterStore2.add(crustMediumGF);
        itemsForSisterStore2.add(crustSmallGF);
        itemsForSisterStore2.add(sauceGF);
        itemsForSisterStore2.add(cheeseGF);
        itemsForSisterStore2.add(pineappleGF);
        itemsForSisterStore2.add(hamGF);
        itemsForSisterStore2.add(shrimpGF);
        itemsForSisterStore2.add(mushroomsGF);
        itemsForSisterStore2.add(sausageGF);
        itemsForSisterStore2.add(greenPeppersGF);
        itemsForSisterStore2.add(onionsGF);
        itemsForSisterStore2.add(olivesGF);
        itemsForSisterStore2.add(pepperoniGF);


        //Establish Stores
        Store mainStore = new Store("Main Location", "6010 15th Ave NW Seattle, WA 98107 United States", false, itemsForMainStore);
        Store sisterStore1 = new Store("Sister Store 1", "601 N 34th St Seattle, WA 98103 United States", true, itemsForSisterStore1);
        Store sisterStore2 = new Store("Sister Store 2", "1302 6th Ave Seattle, WA  98101 United States", true, itemsForSisterStore2);
        storeRepository.save(mainStore);
        storeRepository.save(sisterStore1);
        storeRepository.save(sisterStore2);
    }

    /**
     * Delete all data in all repositories to clear the data for data entry input
     */
    public void clearAllRepositories() {
        menuItemRepository.deleteAll();
        pizzaRepository.deleteAll();
        storeRepository.deleteAll();
        orderRepository.deleteAll();
    }


}
