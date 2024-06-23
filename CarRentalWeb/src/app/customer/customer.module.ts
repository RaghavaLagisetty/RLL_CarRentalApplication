import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyRentalsComponent } from './my-rentals/my-rentals.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { SearchCarsComponent } from './search-cars/search-cars.component';
import { ChangePasswordComponent } from './change-password/change-password.component';


@NgModule({
  declarations: [
    CustomerComponent,
    DashboardComponent,
    CarDetailComponent,
    MyRentalsComponent,
    UserDetailsComponent,
    SearchCarsComponent,
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class CustomerModule { }
