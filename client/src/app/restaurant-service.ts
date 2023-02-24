import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, firstValueFrom } from "rxjs";
import { Restaurant } from "./models";

@Injectable()
export class RestaurantService {

	// declare a subject to emit event out and for other components to listen
    // Create a BehaviorSubject to hold the string for cuisine searched
    onCuisineSearch: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null);
    // Make the BehaviorSubject an observable to other components
    onCuisineSearch$ = this.onCuisineSearch.asObservable();

	constructor(private http: HttpClient) {}
	// TODO Task 2 
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getCuisineList(): Promise<string[]> {
		return firstValueFrom<string[]>(
			this.http.get<string[]>('/api/cuisines')
		)
	}

	// TODO Task 3 
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getRestaurantsByCuisine(cuisine: string): Promise<Restaurant[]> {
		return firstValueFrom<Restaurant[]>(
			this.http.get<Restaurant[]>(`/api/${cuisine}/restaurants`)
		)
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public getRestaurant(id: string): Promise<Restaurant> {
		return firstValueFrom<Restaurant>(
			this.http.get<Restaurant>(`/api/cuisine/${id}`)
		)
	}

	// TODO Task 5
	// Use this method to submit a comment
	// DO NOT CHANGE THE METHOD'S NAME OR SIGNATURE
	public postComment(comment: Comment): Promise<any> {

		const headers = new HttpHeaders()
		.set('Content-Type', 'application/json')
		.set('Accept', 'application/json')

		return firstValueFrom(
			this.http.post<Comment>(`/api/comments`, { headers: headers} )
		)

	}

}