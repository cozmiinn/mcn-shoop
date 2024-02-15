import {CanActivateFn, Router} from '@angular/router';

import {ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from "rxjs";
import {Data_userService} from "./data/user_data/data_user.service";
import {Inject} from "@angular/core";


export const AuthGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
):
  Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree => {

  return Inject(Data_userService).isSellerLoggedIn;

};
