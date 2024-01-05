import {Component, OnInit} from '@angular/core';
import {DataU} from "../../data.service";
import {CommonModule, NgForOf} from "@angular/common";

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {
  users: DataU[] = [];

  constructor(private userService: DataU) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data: DataU[]) => {
        console.log(data);
        this.users = data;
      }
    )
  }

}
