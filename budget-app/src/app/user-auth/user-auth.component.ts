import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';
import {NgForm} from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-auth',
  templateUrl: './user-auth.component.html',
  styleUrls: ['./user-auth.component.css']
})

export class UserAuthComponent implements OnInit {

  user:User;
  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  login(userName:string, password:string): void {
    
    console.log(userName + " " + password )
    this.userService.login(userName,password).subscribe(result => this.user = result)
    // this.budgetService.getBudgetByUserId(user_id)
    //     .subscribe(result => this.budget = result);
  }

  register(firstName:string, lastName:string, email:string, userName:string, password:string) {
    let user = new User(firstName,lastName, email, userName,password)    

    this.userService.register(user).subscribe(result => this.user = result);

  }
}
