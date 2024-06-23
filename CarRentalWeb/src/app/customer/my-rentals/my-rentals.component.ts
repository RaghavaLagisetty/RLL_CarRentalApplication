import { Component } from '@angular/core';
import { CustomerServiceService } from '../customer-service/customer-service.service';

@Component({
  selector: 'app-my-rentals',
  templateUrl: './my-rentals.component.html',
  styleUrls: ['./my-rentals.component.scss']
})
export class MyRentalsComponent {

  rentals;

  constructor(private customerServiceService: CustomerServiceService) {}

  ngOnInit(): void {
    this.getAllRentals();
  }
  
  getAllRentals(){
    this.customerServiceService.getMyRentals().subscribe(res=>{
      this.rentals = res;
      console.log(this.rentals);
    })
  }

  updateRentalStatus(id, status){
    this.customerServiceService.updateRentalStatus(id, status).subscribe(res=>{
      this.getAllRentals();
    }, error=>{
      console.log("error", error)
    })
  }
}
