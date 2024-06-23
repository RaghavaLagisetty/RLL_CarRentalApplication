import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { MyRentalsComponent } from './my-rentals/my-rentals.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { SearchCarsComponent } from './search-cars/search-cars.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'car-detail/:id', component: CarDetailComponent },
  { path: 'my-rentals', component: MyRentalsComponent },
  { path: 'my-profile', component: UserDetailsComponent },
  { path: 'search', component: SearchCarsComponent },
  { path: 'change-password', component: ChangePasswordComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
