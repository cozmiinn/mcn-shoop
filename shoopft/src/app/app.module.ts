import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from "./app.routes";
import {Data_userService} from "./data/user_data/data_user.service";
import {Data_productsService} from "./data/products_data/baseProduct_data/data_products.service";

@NgModule({
  declarations: [],
  imports: [
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [Data_userService, Data_productsService],
  exports: [],
  bootstrap: []
})
export class AppModule {
}

