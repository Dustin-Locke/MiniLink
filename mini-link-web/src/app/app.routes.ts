import { Routes }              from '@angular/router';
import { MainLayoutComponent } from './layouts/main-layout.component';
import { HomeComponent }       from './pages/home/home.component';
import { AboutComponent }      from './pages/about/about.component';
import { ApiComponent }        from './pages/api/api.component';
import { AnalyticsComponent }  from './pages/analytics/analytics.component'


export const routes: Routes = [
  {
    path     : '',
    component: MainLayoutComponent,
    children : [
      {
        path     : '',
        component: HomeComponent
      },
      {
        path     : 'about',
        component: AboutComponent
      },
      {
        path     : 'api',
        component: ApiComponent
      },
      {
        path     : 'analytics',
        component: AnalyticsComponent
      }
    ]
  }
];
