package vttp2022.csf.assessment.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
