import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerServiceService } from '../customer-service/customer-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-car-detail',
  templateUrl: './car-detail.component.html',
  styleUrls: ['./car-detail.component.scss']
})
export class CarDetailComponent {

  car: any;
  user:any;
  bookingForm: FormGroup;
  carId = this.route.snapshot.paramMap.get('id');


  constructor(private route: ActivatedRoute, 
    private customerServiceService: CustomerServiceService,
    private fb: FormBuilder,
    private router: Router) {}

  ngOnInit() {
    this.bookingForm = this.fb.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      startOdometerReading: ['', Validators.required],
      endOdometerReading: ['', Validators.required],
    });
   this.getCarDetails();
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

  getCarDetails(){
    
    if (this.carId) {
      // Fetch the specific car details using your CarService
      this.customerServiceService.getCarById(this.carId).subscribe((car) => {
        this.car = car;
      
      }, error=>{
        console.log("error", error)
      });
    }
  }

  bookCar() {
    // Implement booking logic here using the form values
    console.log('Booking form values:', this.bookingForm.value);
    // You can call a service to handle the booking process
    if(this.bookingForm.valid){
      const data =  this.bookingForm.value;
      data.carId = this.carId;
      this.customerServiceService.rentCar(data).subscribe(res=>{
        console.log("sucess", res);
        this.router.navigateByUrl('/customer/my-rentals');
      }, error=>{
        console.log("error", error);
      })
    }
  }

}
