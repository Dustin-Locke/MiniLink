import { Component, OnInit } from '@angular/core';
import { AnalyticsService, AnalyticsResponse } from './analytics.service';
import { CommonModule, DatePipe }              from '@angular/common';

@Component( {
              selector   : 'app-analytics',
              templateUrl: './analytics.component.html',
              standalone : true,
              imports    : [
                DatePipe,
                CommonModule
              ],
              styleUrl   : './analytics.component.scss'
            })
export class AnalyticsComponent implements OnInit {

  analytics?: AnalyticsResponse;

  constructor(
    private analyticsService: AnalyticsService
  ) {}

  ngOnInit(): void {
    this.analyticsService.getAnalytics()
        .subscribe(response => {
          this.analytics = response;
        });
  }
}
