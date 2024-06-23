import { Component } from '@angular/core';
import { AdminCarService } from '../admin-service/admin-car.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  allCars:any

  constructor(private adminCarService: AdminCarService){}

  ngOnInit(): void {
    this.getAllCars();
  }

  getAllCars(){
    this.adminCarService.getAllCars().subscribe(res=>{
      this.allCars = res;
      console.log(this.allCars)
    })
  }

  deleteCar(id){
    this.adminCarService.deleteCar(id).subscribe(res=>{
      this.getAllCars();
    }, error=>{
      console.log("error", error)
    })
  }

}
