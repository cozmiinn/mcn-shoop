import {Component, OnInit} from '@angular/core';
import {User} from "../../data/user/users-data/user-data";
import {UserService} from "../../data/user/users-data/user.service";
import {CommonModule} from "@angular/common";


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
  users: User[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data: User[]) => {
        console.log(data);
        this.users = data;
      }
    )
  }
}
