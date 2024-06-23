import { Component } from '@angular/core';
import { CustomerServiceService } from '../customer-service/customer-service.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent {

  user:any;

  constructor( 
    private customerServiceService: CustomerServiceService,) {}

  ngOnInit() {
    this.getUserDetails();
  }

  getUserDetails(){
    this.customerServiceService.getUserDetails().subscribe(res=>{
      console.log(res);
      this.user= res;
    }, error=>{
      console.log("error", error);
    })
  }

}
