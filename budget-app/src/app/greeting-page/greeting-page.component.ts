import { Component, OnInit } from '@angular/core';
import { BudgetService } from '../services/budget.service'
import { Greeting } from '../classes/greeting'

@Component({
  selector: 'app-greeting-page',
  templateUrl: './greeting-page.component.html',
  styleUrls: ['./greeting-page.component.css']
})
export class GreetingPageComponent implements OnInit {

  greeting: Greeting;

  constructor(private budgetService: BudgetService) { }

  ngOnInit() {
  }

  getGreeting(name: string) {
    this.budgetService.getContent(name)
                      .subscribe(greeting => this.greeting = greeting)
  }

}
