import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Budget } from '../classes/budget';
import { Category } from '../classes/category';

@Injectable({
  providedIn: 'root'
})
export class BudgetCreateService {
  serverUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/budgetCreateController/budgetCreate'
  serverUrlCat: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/budgetCreateController/CatCreate'
  //serverUrl: string = 'http://localhost:8080/budgetCreateController/budgetCreate'
  //serverUrlCat: string = 'http://localhost:8080/budgetCreateController/CatCreate'
  constructor(private http: HttpClient) {}

  createBudget(budget: Budget): Observable<Budget> {
    return this.http.post<Budget>(this.serverUrl, budget);
  }
  createCat(category_list: string[], category_expense: string[], user_id_in: number, budget_id_in: number): Observable<Category> {
    var cat = new Category();
    cat.names = category_list;
    cat.expenses = category_expense;
    cat.user_id = user_id_in;
    cat.budget_id = budget_id_in;
    console.log("USER ID FROM WEBSITE");
    console.log(cat.user_id);
    return this.http.post<Category>(this.serverUrlCat, cat);
  }
}
