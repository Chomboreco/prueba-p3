import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { IConfirmationData, Login } from 'src/app/interfaces/patito.interfaces';
import { ApiRestService } from 'src/app/services/api-rest.service';
import { SnackComponent } from 'src/app/shared/snack/snack.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginModel: Login = {} as Login;

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);

  constructor(private _apiRest: ApiRestService, private _snackBar: MatSnackBar, private router: Router) { }

  loginSubmit() {
    const snackData: IConfirmationData = { text: 'Registro agregado exitosamente', type: 'o' };
    this._apiRest.login(this.loginModel).subscribe({
      next: (res1) => {
        console.log(res1);
        localStorage.setItem('auth', JSON.stringify(res1));
        
        
        
        this.router.navigate(['/home']);
      },
      error: () => {
        const snackData: IConfirmationData = { text: 'Credenciales no válidas', type: 'e' };
        this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
      },
      complete: () => {
        this.router.navigate(['/home']);
      }
    });
  }
}
