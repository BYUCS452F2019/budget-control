export class User{
    constructor(firstName:string, lastName:string, email:string, userName:string, password:string) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    username:string;
    password:string;
    userId:number;
    email:string;
    firstName:string;
    lastName:string;
    lastLoggedIn:Date;
}