import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'basket',
        loadChildren: () => import('./basket/basket.module').then(m => m.CardoholicBasketModule),
      },
      {
        path: 'countries',
        loadChildren: () => import('./countries/countries.module').then(m => m.CardoholicCountriesModule),
      },
      {
        path: 'items',
        loadChildren: () => import('./items/items.module').then(m => m.CardoholicItemsModule),
      },
      {
        path: 'item-codes',
        loadChildren: () => import('./item-codes/item-codes.module').then(m => m.CardoholicItemCodesModule),
      },
      {
        path: 'item-prices',
        loadChildren: () => import('./item-prices/item-prices.module').then(m => m.CardoholicItemPricesModule),
      },
      {
        path: 'levels',
        loadChildren: () => import('./levels/levels.module').then(m => m.CardoholicLevelsModule),
      },
      {
        path: 'orders',
        loadChildren: () => import('./orders/orders.module').then(m => m.CardoholicOrdersModule),
      },
      {
        path: 'order-items',
        loadChildren: () => import('./order-items/order-items.module').then(m => m.CardoholicOrderItemsModule),
      },
      {
        path: 'users',
        loadChildren: () => import('./users/users.module').then(m => m.CardoholicUsersModule),
      },
      {
        path: 'user-type',
        loadChildren: () => import('./user-type/user-type.module').then(m => m.CardoholicUserTypeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CardoholicEntityModule {}
