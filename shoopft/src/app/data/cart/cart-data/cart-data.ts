import {User} from "../../user/users-data/user-data";
import {Vouchers} from "../vouchers-data/vouchers-data";
import {CartEntries} from "../cartEntries-data/cartEntries-data";

export class Cart {
  id!: number;
  user!: User[];
  vouchers!: Vouchers[];
  cartEntries!: CartEntries[];
  totalPrice!: number;
}
