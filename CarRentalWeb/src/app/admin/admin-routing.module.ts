import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { ViewRentalsComponent } from './view-rentals/view-rentals.component';
import { UpdateCarComponent } from './update-car/update-car.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'create-car', component: CreateCarComponent },
  { path: 'view-rentals', component: ViewRentalsComponent },
  { path: 'update-car/:id', component: UpdateCarComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
