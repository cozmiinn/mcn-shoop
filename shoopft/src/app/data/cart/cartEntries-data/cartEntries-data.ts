import {ProductsVariant} from "../../products/productsVariant-data/productsVariant-data";

export class CartEntries {
  id!: number;
  variants: ProductsVariant[] = [];
  quantity!: number;
  pricePerEntry!: number;
  totalPricePerEntry!: number;

}
