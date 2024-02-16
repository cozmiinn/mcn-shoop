import {Addresses} from "./adress";
import {PaymentCards} from "./paymentCards";

export class User_data {
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
