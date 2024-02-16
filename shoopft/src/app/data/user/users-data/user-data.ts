import {Addresses} from "../addresses-data/addresses-data";
import {PaymentCards} from "../cards-data/cards-data";

export class User {
  id!: number;
  firstName!: string;
  lastName!: string;
  middleName!: string;
  email!: string;
  phoneNumber!: number;
  password!: string;
  address!: Addresses[];
  paymentCard!: PaymentCards[];
}
