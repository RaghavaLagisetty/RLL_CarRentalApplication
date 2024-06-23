import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ViewRentalsComponent } from './view-rentals/view-rentals.component';
import { UpdateCarComponent } from './update-car/update-car.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    CreateCarComponent,
    ViewRentalsComponent,
    UpdateCarComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
