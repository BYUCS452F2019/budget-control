import { Injectable } from '@angular/core';
import { Greeting } from '../classes/greeting'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  // the initial 'https://cors-anywhere.herokuapp.com/URL' is to get around CORS
  serverUrl: string = 'https://cors-anywhere.herokuapp.com/' +
                      'http://ec2-18-191-122-27.us-east-2.compute.amazonaws.com:8080/greeting?name='

  constructor(private http: HttpClient) { }

  getContent(name: string): Observable<Greeting> {
    return this.http.get<Greeting>(this.serverUrl.concat(name))
  }
}
