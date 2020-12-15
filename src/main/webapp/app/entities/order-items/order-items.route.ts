import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrderItems, OrderItems } from 'app/shared/model/order-items.model';
import { OrderItemsService } from './order-items.service';
import { OrderItemsComponent } from './order-items.component';
import { OrderItemsDetailComponent } from './order-items-detail.component';
import { OrderItemsUpdateComponent } from './order-items-update.component';

@Injectable({ providedIn: 'root' })
export class OrderItemsResolve implements Resolve<IOrderItems> {
  constructor(private service: OrderItemsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderItems> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((orderItems: HttpResponse<OrderItems>) => {
          if (orderItems.body) {
            return of(orderItems.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrderItems());
  }
}

export const orderItemsRoute: Routes = [
  {
    path: '',
    component: OrderItemsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.orderItems.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OrderItemsDetailComponent,
    resolve: {
      orderItems: OrderItemsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.orderItems.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OrderItemsUpdateComponent,
    resolve: {
      orderItems: OrderItemsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.orderItems.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OrderItemsUpdateComponent,
    resolve: {
      orderItems: OrderItemsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'cardoholicApp.orderItems.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
