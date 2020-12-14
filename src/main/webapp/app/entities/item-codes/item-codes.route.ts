import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IItemCodes, ItemCodes } from 'app/shared/model/item-codes.model';
import { ItemCodesService } from './item-codes.service';
import { ItemCodesComponent } from './item-codes.component';
import { ItemCodesDetailComponent } from './item-codes-detail.component';
import { ItemCodesUpdateComponent } from './item-codes-update.component';

@Injectable({ providedIn: 'root' })
export class ItemCodesResolve implements Resolve<IItemCodes> {
  constructor(private service: ItemCodesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IItemCodes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((itemCodes: HttpResponse<ItemCodes>) => {
          if (itemCodes.body) {
            return of(itemCodes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ItemCodes());
  }
}

export const itemCodesRoute: Routes = [
  {
    path: '',
    component: ItemCodesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemCodes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ItemCodesDetailComponent,
    resolve: {
      itemCodes: ItemCodesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemCodes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ItemCodesUpdateComponent,
    resolve: {
      itemCodes: ItemCodesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemCodes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ItemCodesUpdateComponent,
    resolve: {
      itemCodes: ItemCodesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemCodes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
