import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';
import {NgForm} from '@angular/forms';
import { UserService } from '../services/user.service';
import { Globals } from '../Globals';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-auth',
  templateUrl: './user-auth.component.html',
  styleUrls: ['./user-auth.component.css']
})

export class UserAuthComponent implements OnInit {

  user:User;
  constructor(private userService: UserService, private globals: Globals, private router: Router) { }

  ngOnInit() {
  }

  login(userName:string, password:string): void {
    
    this.userService.login(userName,password).subscribe(result => {
      this.user = result
      this.globals.user = this.user;
      this.router.navigateByUrl('/transaction');
    })
    // this.budgetService.getBudgetByUserId(user_id)
    //     .subscribe(result => this.budget = result);
  }

  register(firstName:string, lastName:string, email:string, userName:string, password:string) {
    let user = new User(firstName,lastName, email, userName,password)    

    this.userService.register(user).subscribe(result => {
      this.user = result
      if (result == null) {
        console.log('user already exists');
      }
    });

  }
}
