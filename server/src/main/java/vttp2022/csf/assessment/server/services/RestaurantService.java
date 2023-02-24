package vttp2022.csf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;
// import vttp2022.csf.assessment.server.repositories.MapCache;
import vttp2022.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	// @Autowired
	// private MapCache mapCache;

	// TODO Task 2 
	// Use the following method to get a list of cuisines 
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public String getCuisines() {
		
        List<String> cuisines = this.restaurantRepo.getCuisines();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (String c: cuisines)
			arrBuilder.add(c);

        JsonArray jsonArrCuisines = arrBuilder.build();

        // convert the json array into a string and replace '/' with '_'
        String jsonArrCuisinesStr = jsonArrCuisines.toString().replace('/', '_');

        System.out.println(jsonArrCuisinesStr);

        return jsonArrCuisinesStr;
	}


	// TODO Task 3 
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public String getRestaurantsByCuisine(String cuisine) {
		
        // replace '_' with '/'
        String cuisineSlash = cuisine.replace("_", "/");

        List<Restaurant> restaurants = this.restaurantRepo.getRestaurantsByCuisine(cuisineSlash);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (Restaurant r: restaurants)
			arrBuilder.add(r.toJson());

        JsonArray jsonArrRestaurant = arrBuilder.build();

        String jsonArrRestaurantStr = jsonArrRestaurant.toString();

        System.out.println(jsonArrRestaurantStr);

		return jsonArrRestaurantStr;
	}


    // 	TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public Optional<Restaurant> getRestaurant(String id) {
        return this.restaurantRepo.getRestaurant(id);
	}



	// // TODO Task 5
	// // Use this method to insert a comment into the restaurant database
	// // DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// public void addComment(Comment comment) {
	// 	// Implmementation in here
		
	// }
	// //
	// // You may add other methods to this class
}
