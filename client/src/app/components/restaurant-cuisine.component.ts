import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit, OnDestroy{
	
	// TODO Task 3
	// For View 2

  cuisine = ''
  params$!: Subscription
  restaurants: Restaurant[] = []

  constructor(private activatedRoute: ActivatedRoute, private restaurantSvc: RestaurantService) {}

  ngOnInit(): void {
    console.info(">>> RestaurantCuisineComponent: ngOnInit called.")
    // retrieve the route from CuisineListComponent, activate the route in .html
    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        this.cuisine = params['cuisine']
        console.info('>>> RestaurantCuisineComponent: cuisine: ', this.cuisine)
        // make a http request to get a list of restaurant for the given cuisine param
        this.restaurantSvc.getRestaurantsByCuisine(this.cuisine)
          .then(result => {
            console.info(">>> RestaurantCuisineComponent: result: ", result)
            this.restaurants = result
          })
          .catch(error => {
            console.error('>>> RestaurantCuisineComponent: error: ', error)
          })
      }
    )
  }


  ngOnDestroy(): void {
      this.params$.unsubscribe
  }


}
