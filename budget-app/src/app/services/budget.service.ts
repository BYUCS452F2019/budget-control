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

  // TODO This ain't working
  serverUrl: string = 'https://cors-anywhere.herokuapp.com/' +
                      'http://localhost:8080/budget/id?budget_id='
  constructor(private http: HttpClient) { }

  getBudget(budget_id: string): Observable<Budget> {
    return this.http.get<Budget>(this.serverUrl.concat(budget_id))
  }
}
