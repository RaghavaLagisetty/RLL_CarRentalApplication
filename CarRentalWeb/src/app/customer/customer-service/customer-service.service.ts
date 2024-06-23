import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/basic/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

  constructor(private http: HttpClient) { }

  getCarById(id:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/car/${id}`);
  }

  rentCar(data:any): Observable<any> {
    data.userId = UserStorageService.getUserId();
    return this.http.post(BASIC_URL + `api/rentals`, data);
  }

  getMyRentals(): Observable<any> {
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/my-rentals/${userId}`);
  }

  updateRentalStatus(id, rentalStatus): Observable<any> {
    return this.http.get(BASIC_URL + `api/rentals/${id}/${rentalStatus}`);
  }

  getUserDetails(): Observable<any> {
    const id = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/user/${id}`);
  }

  searchCarsByModel(model:string): Observable<any> {
    return this.http.get(BASIC_URL + `api/user/search/${model}`);
  }

  changePassword(data): Observable<any> {
    data.id = UserStorageService.getUserId();
    return this.http.patch(BASIC_URL + `api/user/change-password`, data);
  }
}
