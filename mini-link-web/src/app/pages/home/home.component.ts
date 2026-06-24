import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule }                 from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient }                   from '@angular/common/http';

@Component({
             selector: 'app-home',
             standalone: true,
             imports: [CommonModule, FormsModule],
             templateUrl: './home.component.html',
             styleUrls: ['./home.component.scss'],
           })
export class HomeComponent implements OnInit, OnDestroy {

  BASE_URL: string = 'http://localhost:8080/api';
  longUrl: string = '';
  loading: boolean = false;
  shortUrl: string | null = null;
  customAlias: string = '';
  error: string | null = null;

  constructor(private http: HttpClient) {}

  createLink(): void {
    if (!this.longUrl.trim()) return;

    this.loading = true;
    this.error = null;
    this.shortUrl = null;

    this.http.post<any>('http://localhost:8080/api', {
      originalUrl: this.longUrl,
      customAlias: this.customAlias?.trim() || null
    }).subscribe({
                   next: (res) => {
                     this.shortUrl = res.shortUrl || res;
                     this.loading = false;

                     // optional UX improvement
                     this.customAlias = '';
                   },
                   error: (err) => {
                     this.error = err.error.message;
                     this.loading = false;
                   }
                 });
  }

  ngOnInit() {}

  ngOnDestroy() {}

}
