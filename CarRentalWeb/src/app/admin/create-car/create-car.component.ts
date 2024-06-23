import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdminCarService } from '../admin-service/admin-car.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-car',
  templateUrl: './create-car.component.html',
  styleUrls: ['./create-car.component.scss']
})
export class CreateCarComponent {

  carForm: FormGroup;
  carTypes: any;
  selectedFile: File | null;

  constructor(private fb: FormBuilder,
    private adminCarService: AdminCarService,
    private router: Router,) {}

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
  }

  getAllCarTypes(){
    this.adminCarService.getAllCarTypes().subscribe(res=>{
      this.carTypes = res;
    })
  }

  onSubmit() {
    const formData = new FormData();
    // formData.append('userName', this.userName);

    if (this.selectedFile) {
      formData.append('image', this.selectedFile);

      formData.append('make', this.carForm.get('make').value);
      formData.append('model', this.carForm.get('model').value);
      formData.append('year', this.carForm.get('year').value);
      formData.append('odometerReading', this.carForm.get('odometerReading').value);
      formData.append('cartype', this.carForm.get('cartype').value);
      formData.append('costPerKilometer', this.carForm.get('costPerKilometer').value);
    

        this.adminCarService.createCar(formData).subscribe(res =>{
          console.log(res);
          this.router.navigateByUrl('/admin/dashboard');
        }, error=>{
          console.log(error);
        })
        
        console.log(formData);
    }
  }

  onFileChange(event: any) {
    this.selectedFile =  event.target.files[0];
  }



}
