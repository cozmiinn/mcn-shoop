import {Attributess} from "../attribute-data/attribute-data";

export class ProductsVariant {
  id!: number;
  name!: string;
  description!: string;
  price!: number;
  availableQuantity!: boolean;
  addedDate!: Date;
  attribute!: Attributess[];

}
