import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component( {
              selector   : 'app-api',
              imports    : [CommonModule, FormsModule],
              templateUrl: './api.component.html',
              standalone : true,
              styleUrl   : './api.component.scss'
            })
export class ApiComponent {

}
