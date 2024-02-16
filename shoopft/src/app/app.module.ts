import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from "./app.routes";
import {UserService} from "./data/user/users-data/user.service";
import {BaseProductsService} from "./data/products/baseProduct-data/baseProducts-data.service";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {ProductsVariantService} from "./data/products/productsVariant-data/productsVariant-data.service";

@NgModule({
  declarations: [],
  imports: [
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [UserService, BaseProductsService, ProductsVariantService],
  exports: [],
  bootstrap: []
})
export class AppModule {
}

