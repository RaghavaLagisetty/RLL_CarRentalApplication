import { Component } from '@angular/core';
import { AdminCarService } from '../admin-service/admin-car.service';

@Component({
  selector: 'app-view-rentals',
  templateUrl: './view-rentals.component.html',
  styleUrls: ['./view-rentals.component.scss']
})
export class ViewRentalsComponent {

  rentals;

  constructor(private adminCarService: AdminCarService) {}

  ngOnInit(): void {
    this.getAllRentals();
  }
  
  getAllRentals(){
    this.adminCarService.getAllRentals().subscribe(res=>{
      this.rentals = res;
      console.log(this.rentals);
    })
  }

  updateRentalStatus(id, status){
    this.adminCarService.updateRentalStatus(id, status).subscribe(res=>{
      this.getAllRentals();
    }, error=>{
      console.log("error", error)
    })
  }

}
