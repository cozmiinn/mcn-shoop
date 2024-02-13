import {DataProductsVariant} from "../productsVariant_data/data-productsVariant";

export class DataBaseProducts {
  id!: number;
  type!: string;
  productVariants!: DataProductsVariant[];
}
