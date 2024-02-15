import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from "./app.routes";
import {Data_userService} from "./data/user_data/data_user.service";
import {
  DataBaseProductsService
} from "./data/products_data/baseProduct_data/data_baseProducts.service";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {
  DataProductsVariantService
} from "./data/products_data/productsVariant_data/data-productsVariant.service";
import {AuthGuard} from "./auth.guard";

@NgModule({
  declarations: [],
  imports: [
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [Data_userService, DataBaseProductsService, DataProductsVariantService],
  exports: [],
  bootstrap: []
})
export class AppModule {
}

