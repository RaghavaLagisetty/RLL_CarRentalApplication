import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerServiceService } from '../customer-service/customer-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {
  changePasswordForm: FormGroup;

  error = null;

  constructor(private fb: FormBuilder,
    private router: Router,
    private customerServiceService: CustomerServiceService) {
    this.changePasswordForm = this.fb.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(group: FormGroup) {
    const newPassword = group.get('newPassword').value;
    const confirmPassword = group.get('confirmPassword').value;

    return newPassword === confirmPassword ? null : { passwordMismatch: true };
  }

  onChangePasswordSubmit() {
    if (this.changePasswordForm.valid) {
      // Implement your logic to change the password here
      const currentPassword = this.changePasswordForm.get('currentPassword').value;
      const newPassword = this.changePasswordForm.get('newPassword').value;


      this.customerServiceService.changePassword(this.changePasswordForm.value).subscribe(
        (response) => {
          // Handle success
          this.router.navigateByUrl('/customer/dashboard');
        },
        (error) => {
          this.error = error.error
        }
      );
    }
  }
}
