package vttp2022.csf.assessment.server.models;

import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// Do not modify this class
public class Restaurant {
	
	private String restaurantId;
	private String name;
	private String cuisine;
	private String address;
	private LatLng coordinates;
	private String mapUrl;

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantId() {
		return this.restaurantId;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCuisine() {
		return this.cuisine;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}

	public void setCoordinates(LatLng coordinates) {
		this.coordinates = coordinates;
	}
	public LatLng getCoordinates() {
		return this.coordinates;
	}

	public void setMapURL(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getMapURL() {
		return this.mapUrl;
	}


	// helper functions
	public static Restaurant create(Document doc) {
		final Restaurant r = new Restaurant();
		r.setRestaurantId(doc.getString("restaurant_id"));
		r.setName(doc.getString("name"));
		r.setCuisine(doc.getString("cuisine"));

		// set address, address is an object
		Document d = doc.get("address", Document.class);
		String building = d.getString("building");
		String street = d.getString("street");
		String zipcode = d.getString("zipcode");
		String borough = doc.getString("borough");
		String address = building + ", " + street + ", " + zipcode + ", " + borough;
		r.setAddress(address);

		// set coord
		// List<LatLng> listOfLatLng = d.getList("coord", LatLng.class);
		// LatLng latLng = new LatLng();
		// latLng.setLatitude(((float) listOfLatLng.get(0).getLatitude()));
		// latLng.setLatitude(((float) listOfLatLng.get(0).getLongitude()));
		// r.setCoordinates(latLng);
		return r;
	}

	public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("restaurantId", restaurantId)
            .add("name", name)
            .add("cuisine", cuisine)
            .add("address", address)
			// .add("coordinates", coordinates.toString())
            .build();
    }
}
