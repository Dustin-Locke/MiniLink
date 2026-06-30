import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule }                  from '@angular/forms';
import { CommonModule }                 from '@angular/common';
import { HeaderComponent }              from '../shared/components/header/header.component';
import { RouterOutlet }                 from '@angular/router';
import { FooterComponent }              from '../shared/components/footer/footer.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
             standalone: true,
             selector: 'main-layout',
             imports: [ CommonModule,
                        FormsModule,
                        HeaderComponent,
                        RouterOutlet,
                        FooterComponent,
                        HttpClientModule],
             templateUrl: './main-layout.component.html',
             styleUrls: ['./main-layout.component.scss']
           })
export class MainLayoutComponent implements OnInit, OnDestroy {

  ngOnInit() {

  }

  ngOnDestroy() {

  }
}
