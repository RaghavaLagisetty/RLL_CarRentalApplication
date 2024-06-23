import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }


  register(data): Observable<any> {
    console.log(data);
    return this.http.post(BASIC_URL + "api/user/register", data);
  }

  login(email, password): Observable<any> {
    const params = new HttpParams().set('email', email).set('password', password);
    return this.http.post(BASIC_URL + "api/user/login", params);
  }
}
