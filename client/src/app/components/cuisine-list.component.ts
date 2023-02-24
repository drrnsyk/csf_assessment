import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})
export class CuisineListComponent implements OnInit {

	// TODO Task 2
	// For View 1

  cuisines: string[] = []

  constructor(private restaurantSvc: RestaurantService) {}

  ngOnInit(): void {
    // on initialization, call service to make http request to get cuisines
    this.restaurantSvc.getCuisineList()
      .then(result => {
        console.info(">>> CuisineListComponent: result: ", result)
        this.cuisines = result
      })
      .catch(error => {
        console.error('>>> CuisineListComponent: error: ', error)
      })
  }

}
