import {Component, OnInit} from '@angular/core';
import {User_data} from "../../data/user_data/user_data";
import {User_dataService} from "../../data/user_data/user_data.service";
import {CommonModule, NgForOf} from "@angular/common";
import {Addresses} from "../../data/user_data/adress";
import {addAriaReferencedId} from "@angular/cdk/a11y";
import {join} from "@angular/compiler-cli";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
  imports: [
    CommonModule
  ],
  standalone: true
})
export class UserComponent implements OnInit {
  users: User_data[] = [];
  adresses: Addresses[] = [];

  constructor(private userService: User_dataService) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data: User_data[]) => {
        console.log(data);
        this.users = data;
      }
    )
  }

  protected readonly Addresses = Addresses;
  protected readonly addAriaReferencedId = addAriaReferencedId;
  protected readonly join = join;
}
