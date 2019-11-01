import { Injectable } from '@angular/core';
import { User } from '../classes/user'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // the initial 'https://cors-anywhere.herokuapp.com/URL' is to get around CORS
  // serverUrl: string = 'https://cors-anywhere.herokuapp.com/' +
  //                     'http://ec2-18-191-122-27.us-east-2.compute.amazonaws.com:8080/greeting?name='

  loginURI: string = 'http://localhost:8080/user/login?'

  registerURI: string = 'http://localhost:8080/user/register'
  constructor(private http: HttpClient) { }

  login(username: string,password:string): Observable<User> {
    return this.http.get<User>(this.loginURI.concat("username="+username+"&password="+password));
  }

  register(user:User): Observable<User> {
    let result = this.http.put<User>(this.registerURI,user);
    console.log(result);

    return result
  }
}
