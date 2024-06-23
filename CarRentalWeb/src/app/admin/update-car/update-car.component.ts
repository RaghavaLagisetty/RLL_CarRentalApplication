import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminCarService } from '../admin-service/admin-car.service';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.scss']
})
export class UpdateCarComponent {

  carForm: FormGroup;
  carTypes: any;
  selectedFile: File | null;
  carId = this.route.snapshot.paramMap.get('id');

  constructor(private fb: FormBuilder,
    private adminCarService: AdminCarService,
    private router: Router,
    private route: ActivatedRoute,) { }

  ngOnInit(): void {
    this.carForm = this.fb.group({
      make: ['', Validators.required],
      model: ['', Validators.required],
      year: [null, [Validators.required, Validators.pattern(/^\d{4}$/)]],
      odometerReading: [null, Validators.required],
      cartype: [null, Validators.required],
      costPerKilometer: [null, Validators.required],

    });
    this.getAllCarTypes();
    this.getCarDetails();
  }

  getAllCarTypes() {
    this.adminCarService.getAllCarTypes().subscribe(res => {
      this.carTypes = res;

    })
  }

  getCarDetails() {
    if (this.carId) {
      // Fetch the specific car details using your CarService
      this.adminCarService.getCarById(this.carId).subscribe((car) => {
        this.carForm.patchValue(car);
        this.carForm.get("cartype").patchValue(car.cartype.id);
        console.log(car);

      }, error => {
        console.log("error", error)
      });
    }
  }

  onSubmit() {
    const formData = new FormData();
    // formData.append('userName', this.userName);

    if (this.carForm.valid) {
      if (this.selectedFile) {
        formData.append('image', this.selectedFile);
      }

      formData.append('make', this.carForm.get('make').value);
      formData.append('model', this.carForm.get('model').value);
      formData.append('year', this.carForm.get('year').value);
      formData.append('odometerReading', this.carForm.get('odometerReading').value);
      formData.append('cartype', this.carForm.get('cartype').value);
      formData.append('costPerKilometer', this.carForm.get('costPerKilometer').value);


      this.adminCarService.updateCar(this.carId, formData).subscribe(res => {
        console.log(res);
        this.router.navigateByUrl('/admin/dashboard');
      }, error => {
        console.log(error);
      })

      console.log(formData);
    }
  }


  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }



}

