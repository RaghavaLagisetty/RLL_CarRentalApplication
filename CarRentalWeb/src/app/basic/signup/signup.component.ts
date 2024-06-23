import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  signupForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private router: Router) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    // Handle form submission logic here
    console.log(this.signupForm.value);
    if(this.signupForm.valid){
      this.authService.register(this.signupForm.value).subscribe(res=>{
        this.router.navigateByUrl('/login');
      }, error=>{
        console.log(error)
      })
    }
  }

}
