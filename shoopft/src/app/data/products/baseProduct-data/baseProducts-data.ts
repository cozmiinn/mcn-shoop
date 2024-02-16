import {ProductsVariant} from "../productsVariant-data/productsVariant-data";

export class BaseProducts {
  id!: number;
  type!: string;
  productVariants!: ProductsVariant[];
}
