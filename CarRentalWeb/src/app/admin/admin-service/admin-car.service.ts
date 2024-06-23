import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AdminCarService {

  constructor(private http: HttpClient) { }


  createCar(data): Observable<any> {
    console.log(data)
    return this.http.post(BASIC_URL + "api/admin/cars", data);
  }

  updateCar(id,data): Observable<any> {
    console.log(data)
    return this.http.put(BASIC_URL + `api/admin/cars/${id}`, data);
  }

  deleteCar(id): Observable<any> {
    return this.http.delete(BASIC_URL + `api/admin/cars/${id}`, { responseType: 'text' });
  }

  getAllCarTypes(): Observable<any> {
    return this.http.get(BASIC_URL + "api/admin/car-types");
  }

  getAllCars(): Observable<any> {
    return this.http.get(BASIC_URL + "api/admin/cars");
  }

  getAllRentals(): Observable<any> {
    return this.http.get(BASIC_URL + "api/admin/rentals");
  }


  updateRentalStatus(id, rentalStatus): Observable<any> {
    return this.http.get(BASIC_URL + `api/rentals/${id}/${rentalStatus}`);
  }

  getCarById(id:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/car/${id}`);
  }
}
