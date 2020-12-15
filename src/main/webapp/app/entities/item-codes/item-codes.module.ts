import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardoholicSharedModule } from 'app/shared/shared.module';
import { ItemCodesComponent } from './item-codes.component';
import { ItemCodesDetailComponent } from './item-codes-detail.component';
import { ItemCodesUpdateComponent } from './item-codes-update.component';
import { ItemCodesDeleteDialogComponent } from './item-codes-delete-dialog.component';
import { itemCodesRoute } from './item-codes.route';

@NgModule({
  imports: [CardoholicSharedModule, RouterModule.forChild(itemCodesRoute)],
  declarations: [ItemCodesComponent, ItemCodesDetailComponent, ItemCodesUpdateComponent, ItemCodesDeleteDialogComponent],
  entryComponents: [ItemCodesDeleteDialogComponent],
})
export class CardoholicItemCodesModule {}
