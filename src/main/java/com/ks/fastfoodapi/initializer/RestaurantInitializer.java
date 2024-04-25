package com.ks.fastfoodapi.initializer;

import com.ks.fastfoodapi.model.Restaurant;
import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.RestaurantRepository;
import com.ks.fastfoodapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Order(2)
public class RestaurantInitializer implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantInitializer(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void run(String... args) {
        List<Long> managerIds = new ArrayList<>(List.of(2L, 5L, 8L, 11L));
        Collections.shuffle(managerIds);

        List<String> restaurantNames = List.of(
                "Burger King", "McDonald`s", "Bizim Restaurant", "Hell`s Kitchen", "Dave`s Diner",
                "FastFood House", "Arby`s", "Chipotle", "KFC", "Pizza Hut",
                "Kebab House", "Nusret FF", "Wawa", "Subway", "Sonic Doner"
        );

        List<String> addresses = List.of(
                "12 Elberon Ave", "21 Manhattan Ave", "81 New York Ave", "93 Roberts", "45 5th Ave",
                "Yunus Emre N.", "Goncagul Street", "Fevzi Cakmak Ave", "University Ave", "Ataturk Blvd",
                "Liva Blvd", "Brooklyn Street", "Merkez Blvd", "Samsun Ave", "23 Nisan Street"
        );

        for (int i = 0; i < restaurantNames.size(); i++) {
            Long managerId = managerIds.get(i % managerIds.size());
            User manager = userRepository.findById(managerId)
                    .orElseThrow(() -> new IllegalArgumentException("Restaurant Manager not found with id: " + managerId));

            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantNames.get(i));
            restaurant.setAddress(addresses.get(i));
            restaurant.setManager(manager);
            restaurantRepository.save(restaurant);
        }
    }
}
