import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-user-auth',
  templateUrl: './user-auth.component.html',
  styleUrls: ['./user-auth.component.css']
})

export class UserAuthComponent implements OnInit {

  user:User;
  constructor() { }

  ngOnInit() {
  }

  onSubmit(f: NgForm): void {
    
    console.log(f )
    // this.budgetService.getBudgetByUserId(user_id)
    //     .subscribe(result => this.budget = result);
  }
}
