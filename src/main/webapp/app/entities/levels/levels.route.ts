import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILevels, Levels } from 'app/shared/model/levels.model';
import { LevelsService } from './levels.service';
import { LevelsComponent } from './levels.component';
import { LevelsDetailComponent } from './levels-detail.component';
import { LevelsUpdateComponent } from './levels-update.component';

@Injectable({ providedIn: 'root' })
export class LevelsResolve implements Resolve<ILevels> {
  constructor(private service: LevelsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILevels> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((levels: HttpResponse<Levels>) => {
          if (levels.body) {
            return of(levels.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Levels());
  }
}

export const levelsRoute: Routes = [
  {
    path: '',
    component: LevelsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.levels.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LevelsDetailComponent,
    resolve: {
      levels: LevelsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.levels.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LevelsUpdateComponent,
    resolve: {
      levels: LevelsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.levels.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LevelsUpdateComponent,
    resolve: {
      levels: LevelsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.levels.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
