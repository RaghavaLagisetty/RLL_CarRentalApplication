import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm!: FormGroup;
  error = null;
  activeCaptcha:any;
  captchaResolved = false;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private router: Router) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      captcha: ['', Validators.required],
    });
    this.generateRandomCaptcha(5);
  }

  onSubmit() {
    // Handle form submission logic here
    console.log(this.loginForm.value);
    this.checkCaptcha();
    if(this.loginForm.valid && this.captchaResolved){
      this.authService.login(this.loginForm.get('email').value,this.loginForm.get('password').value).subscribe(res=>{
        // this.router.navigateByUrl('/login');
        console.log(res);
        UserStorageService.saveUser(res);
        if(UserStorageService.isAdminLoggedIn()){
          this.router.navigateByUrl('/admin/dashboard');
        }else if(UserStorageService.isCustomerLoggedIn()){
          this.router.navigateByUrl('/customer/dashboard');
        }
      }, error=>{
        console.log(error);
        this.error = error.error;
      })
    }else if(!this.captchaResolved){
      this.error = "Please reslove captcha."
    }
  }

  generateRandomCaptcha(length: number): string {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let captcha = '';
    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      captcha += characters.charAt(randomIndex);
    }

    this.activeCaptcha = captcha;
    return captcha;
  }

  checkCaptcha(): void {
    if (this.loginForm.get('captcha').value == this.activeCaptcha) {
      this.captchaResolved = true;
      
    } 
  }

}

