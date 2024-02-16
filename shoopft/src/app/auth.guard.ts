import {CanActivateFn} from '@angular/router';

import {ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from "rxjs";
import {User_dataService} from "./data/user_data/user_data.service";
import {Inject} from "@angular/core";


export const AuthGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
):
  Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree => {

  return Inject(User_dataService).isSellerLoggedIn;

};
