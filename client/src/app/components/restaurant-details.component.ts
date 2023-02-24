// import { Component } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit{
	
	// TODO Task 4 and Task 5
	// For View 3

  id = ''
  params$!: Subscription
  restaurant!: Restaurant

  constructor(private activatedRoute: ActivatedRoute, private restaurantSvc: RestaurantService) {}

  ngOnInit(): void {
    console.info(">>> RestaurantDetailsComponent: ngOnInit called.")
    // retrieve the route from RestaurantCuisineComponent, activate the route in .html
    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        this.id = params['id']
        console.info('>>> RestaurantCuisineComponent: id: ', this.id)
        // make a http request to get a list of restaurant for the given cuisine param
        this.restaurantSvc.getRestaurant(this.id)
          .then(result => {
            console.info('>>> RestaurantCuisineComponent: result: ', result)
            this.restaurant = result
          })
          .catch(error => {
            console.error('>>> RestaurantCuisineComponent: error: ', error)
          })
      }
    )
  }

}
