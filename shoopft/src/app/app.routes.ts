import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from "./components/pages/homepage/homepage.component";
import {LoginComponent} from "./components/pages/login/login.component";
import {RegisterComponent} from "./components/pages/register/register.component";
import {Products_searchComponent} from "./components/pages/products-search/products_search.component";
import {ProductComponent} from "./components/product/product.component";
import {CustomerPageComponent} from "./components/pages/customerpage/customerpage.component";
import {AuthGuard} from "./auth.guard";

export const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'customer-page', component: CustomerPageComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'products', component: Products_searchComponent},
  {path: 'product', component: ProductComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
