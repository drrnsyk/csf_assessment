package vttp2022.csf.assessment.server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.JsonObject;
import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;

@Controller
@RequestMapping(path="/api")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantSvc;
    

    @GetMapping(value="/cuisines")
    @ResponseBody
    public ResponseEntity<String> getCuisines() {
        
        String jsonArrCuisinesStr = restaurantSvc.getCuisines();

        return ResponseEntity.ok(jsonArrCuisinesStr);
    }

    @GetMapping(value="/{cuisine}/restaurants")
    @ResponseBody
    public ResponseEntity<String> getRestaurantsByCuisine(@PathVariable String cuisine) {
        
        System.out.printf(">>> query param: cuisine=%s\n", cuisine);

        String jsonArrRestaurantStr = restaurantSvc.getRestaurantsByCuisine(cuisine);

        return ResponseEntity.ok(jsonArrRestaurantStr);
    }

    @GetMapping(value="/cuisine/{id}")
    @ResponseBody
    public ResponseEntity<String> getRestaurant(@PathVariable String id) {

        System.out.printf(">>> query param: id=%s\n", id);

        Optional<Restaurant> opt = restaurantSvc.getRestaurant(id);

        if (opt.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No restaurant found");

        Restaurant restaurant = opt.get();
        JsonObject joRestaurant = restaurant.toJson();
        String joStrRestaurant = joRestaurant.toString();

        System.out.println(joStrRestaurant);

        return ResponseEntity.ok(joStrRestaurant);

    }

    @PostMapping(value="/comments")
    @ResponseBody
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {
        
        this.restaurantSvc.addComment(comment);

        return ResponseEntity.ok(null);
    }

}
