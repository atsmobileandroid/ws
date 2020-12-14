import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardoholicSharedModule } from 'app/shared/shared.module';
import { OrderItemsComponent } from './order-items.component';
import { OrderItemsDetailComponent } from './order-items-detail.component';
import { OrderItemsUpdateComponent } from './order-items-update.component';
import { OrderItemsDeleteDialogComponent } from './order-items-delete-dialog.component';
import { orderItemsRoute } from './order-items.route';

@NgModule({
  imports: [CardoholicSharedModule, RouterModule.forChild(orderItemsRoute)],
  declarations: [OrderItemsComponent, OrderItemsDetailComponent, OrderItemsUpdateComponent, OrderItemsDeleteDialogComponent],
  entryComponents: [OrderItemsDeleteDialogComponent],
})
export class CardoholicOrderItemsModule {}
