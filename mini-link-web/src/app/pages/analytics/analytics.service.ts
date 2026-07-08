import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface PopularLinkResponse {
  miniCode: string;
  clicks: number;
}

export interface LinkEventResponse {
  id: number;
  miniCode: string;
  originalUrl: string;
  eventDate: string;
  eventType: string;
}

export interface AnalyticsResponse {
  totalLinksCreated: number;
  totalClicks: number;
  popularLinks: PopularLinkResponse[];
  recentEvents: LinkEventResponse[];
}

@Injectable({
              providedIn: 'root'
            })
export class AnalyticsService {

  private readonly API_URL = `${environment.apiUrl}/api/analytics`;

  constructor(private http: HttpClient) {}

  getAnalytics(): Observable<AnalyticsResponse> {
    return this.http.get<AnalyticsResponse>(this.API_URL);
  }
}
