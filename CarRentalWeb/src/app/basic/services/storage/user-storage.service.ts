import { Injectable } from '@angular/core';

const USER = 'car_user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }


  static getUserId(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.id;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (this.getUser() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'ADMIN';
  }

  // static getUser(): any {
  //   return JSON.parse(localStorage.getItem(USER));
  // }

  static isCustomerLoggedIn(): boolean {
    if (this.getUser() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'USER';
  }

  static signOut(): void {
    window.localStorage.removeItem(USER);
  }

}
