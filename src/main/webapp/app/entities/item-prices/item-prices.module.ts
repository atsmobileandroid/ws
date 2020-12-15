import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardoholicSharedModule } from 'app/shared/shared.module';
import { ItemPricesComponent } from './item-prices.component';
import { ItemPricesDetailComponent } from './item-prices-detail.component';
import { ItemPricesUpdateComponent } from './item-prices-update.component';
import { ItemPricesDeleteDialogComponent } from './item-prices-delete-dialog.component';
import { itemPricesRoute } from './item-prices.route';

@NgModule({
  imports: [CardoholicSharedModule, RouterModule.forChild(itemPricesRoute)],
  declarations: [ItemPricesComponent, ItemPricesDetailComponent, ItemPricesUpdateComponent, ItemPricesDeleteDialogComponent],
  entryComponents: [ItemPricesDeleteDialogComponent],
})
export class CardoholicItemPricesModule {}
