import { Injectable } from '@angular/core';
import { Budget } from '../classes/budget'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  // the initial 'https://cors-anywhere.herokuapp.com/URL' is to get around CORS
  // serverUrl: string = 'https://cors-anywhere.herokuapp.com/' +
  //                     'http://ec2-18-191-122-27.us-east-2.compute.amazonaws.com:8080/greeting?name='

  budgetIdUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/budget/id?budget_id='
  userIdUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/budget/user_id?user_id='
  constructor(private http: HttpClient) { }

  getBudget(budget_id: string): Observable<Budget> {
    return this.http.get<Budget>(this.budgetIdUrl.concat(budget_id));
  }

  getBudgetByUserId(user_id: string): Observable<Budget> {
    return this.http.get<Budget>(this.userIdUrl.concat(user_id))
  }
}
