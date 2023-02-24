// import { Component } from '@angular/core';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit, OnDestroy{
	
	// TODO Task 4 and Task 5
	// For View 3

  id = ''
  params$!: Subscription
  restaurant!: Restaurant

  commentForm!: FormGroup

  constructor(private activatedRoute: ActivatedRoute, private restaurantSvc: RestaurantService, private fb: FormBuilder) {}

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

    this.commentForm = this.createForm()
    this.commentForm.reset()

  }

  doPostComment() {
    const comment: Comment = this.commentForm.value as Comment
    console.info('>>> RestaurantCuisineComponent: ngSubmit: comment', comment)
    // call service to make the http post request
    this.restaurantSvc.postComment(comment)
  }

  private createForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.min(3)]),
      rating: this.fb.control<string>('1'),
      restaurantId: this.fb.control<string>(this.id),
      text: this.fb.control<string>('', Validators.required)
    })
  }

  ngOnDestroy(): void {
      this.params$.unsubscribe()
  }


}
