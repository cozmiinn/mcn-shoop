import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from "./app.routes";
import {User_dataService} from "./data/user_data/user_data.service";
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
  providers: [User_dataService, DataBaseProductsService, DataProductsVariantService],
  exports: [],
  bootstrap: []
})
export class AppModule {
}

