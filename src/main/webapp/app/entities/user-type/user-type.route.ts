import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserType, UserType } from 'app/shared/model/user-type.model';
import { UserTypeService } from './user-type.service';
import { UserTypeComponent } from './user-type.component';
import { UserTypeDetailComponent } from './user-type-detail.component';
import { UserTypeUpdateComponent } from './user-type-update.component';

@Injectable({ providedIn: 'root' })
export class UserTypeResolve implements Resolve<IUserType> {
  constructor(private service: UserTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userType: HttpResponse<UserType>) => {
          if (userType.body) {
            return of(userType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserType());
  }
}

export const userTypeRoute: Routes = [
  {
    path: '',
    component: UserTypeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.userType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserTypeDetailComponent,
    resolve: {
      userType: UserTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.userType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserTypeUpdateComponent,
    resolve: {
      userType: UserTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.userType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserTypeUpdateComponent,
    resolve: {
      userType: UserTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.userType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
