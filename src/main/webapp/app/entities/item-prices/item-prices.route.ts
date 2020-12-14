import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IItemPrices, ItemPrices } from 'app/shared/model/item-prices.model';
import { ItemPricesService } from './item-prices.service';
import { ItemPricesComponent } from './item-prices.component';
import { ItemPricesDetailComponent } from './item-prices-detail.component';
import { ItemPricesUpdateComponent } from './item-prices-update.component';

@Injectable({ providedIn: 'root' })
export class ItemPricesResolve implements Resolve<IItemPrices> {
  constructor(private service: ItemPricesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IItemPrices> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((itemPrices: HttpResponse<ItemPrices>) => {
          if (itemPrices.body) {
            return of(itemPrices.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ItemPrices());
  }
}

export const itemPricesRoute: Routes = [
  {
    path: '',
    component: ItemPricesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemPrices.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ItemPricesDetailComponent,
    resolve: {
      itemPrices: ItemPricesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemPrices.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ItemPricesUpdateComponent,
    resolve: {
      itemPrices: ItemPricesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemPrices.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ItemPricesUpdateComponent,
    resolve: {
      itemPrices: ItemPricesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.itemPrices.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
