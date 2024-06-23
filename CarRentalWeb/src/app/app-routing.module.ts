import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './basic/signup/signup.component';
import { LoginComponent } from './basic/login/login.component';
import { AboutUsComponent } from './basic/about-us/about-us.component';

const routes: Routes = [
  {path:"sign-up", component: SignupComponent},
  {path:"", component: AboutUsComponent},
  {path:"login", component: LoginComponent},
  { path: 'customer', loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule) },
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
