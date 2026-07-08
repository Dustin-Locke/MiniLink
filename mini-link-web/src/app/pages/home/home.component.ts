import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule }                 from '@angular/common';
import { FormsModule }                  from '@angular/forms';
import { HttpClient }                   from '@angular/common/http';
import { MiniLink }                     from '../../models/mini-link';
import { environment }                  from '../../../environments/environment';

@Component({
             selector: 'app-home',
             standalone: true,
             imports: [CommonModule, FormsModule],
             templateUrl: './home.component.html',
             styleUrls: ['./home.component.scss'],
           })
export class HomeComponent implements OnInit, OnDestroy {

  private readonly API = `${environment.apiUrl}/api`;
  longUrl: string = '';
  loading: boolean = false;
  shortUrl: string | null = null;
  customAlias: string = '';
  error: string | null = null;
  links: MiniLink[] = [];

  showLinks = false;
  inlineErrors: { [key: number]: string } = {};

  toastMessage = '';
  showToast = false;
  toastType: 'success' | 'error' = 'success';
  private toastTimeout: any;

  constructor(private http: HttpClient) {}

  createLink(): void {
    if (!this.longUrl.trim()) return;

    this.loading = true;
    this.error = null;
    this.shortUrl = null;

    this.http.post<any>(this.API, {
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

  loadLinks(): void {
    this.http
        .get<MiniLink[]>( `${this.API}/links`)
        .subscribe({
                     next: (links) => {
                       this.links = links;
                     }
                   });
  }

  toggleLinks(): void {

    this.showLinks = !this.showLinks;

    if (this.showLinks) {
      this.loadLinks();
    }
  }

  copyLink(url: string): void {
    navigator.clipboard.writeText(url);
    this.showNotification('Link copied to clipboard', 'success');
  }

  deleteLink(id: number): void {

    delete this.inlineErrors[id];
    this.http
        .delete(`${this.API}/links/${id}`)
        .subscribe({
                     next: () => {
                       this.links =
                         this.links.filter(link => link.id !== id);

                       this.showNotification('Link deleted', 'error');
                     },
                     error: (err) => {
                       this.inlineErrors[id] =
                         err?.error?.message ?? 'Failed to delete link. Refresh page.';

                        setTimeout(() => {
                          delete this.inlineErrors[id];
                        }, 3000);

                      }

                   });
  }

  showNotification(message: string, type: 'success' | 'error' = 'success'): void {

    // reset existing timer if it exists
    if (this.toastTimeout) {
      clearTimeout(this.toastTimeout);
    }

    this.toastMessage = message;
    this.toastType = type;
    this.showToast = true;

    // start a fresh 3s timer
    this.toastTimeout = setTimeout(() => {
      this.showToast = false;
      this.toastTimeout = null;
    }, 3000);
  }

  ngOnInit() {}

  ngOnDestroy() {}

}
