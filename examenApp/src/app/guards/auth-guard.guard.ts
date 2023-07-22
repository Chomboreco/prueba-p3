import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthResponse } from '../interfaces/patito.interfaces';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {
  canActivate() {

    let loginResponse: AuthResponse = {} as AuthResponse;
    loginResponse = JSON.parse(localStorage.getItem('auth')!);

    console.log("loginResponse guard", loginResponse);
    

    if (loginResponse != null  && loginResponse.accessToken)
      return true;

    return false;
  }

}
