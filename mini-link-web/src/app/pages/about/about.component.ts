import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component( {
              selector   : 'about',
              imports    : [ CommonModule, FormsModule ],
              templateUrl: './about.component.html',
              standalone : true,
              styleUrl   : './about.component.scss'
            })
export class AboutComponent {

}
