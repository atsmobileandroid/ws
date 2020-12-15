import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardoholicSharedModule } from 'app/shared/shared.module';
import { UserTypeComponent } from './user-type.component';
import { UserTypeDetailComponent } from './user-type-detail.component';
import { UserTypeUpdateComponent } from './user-type-update.component';
import { UserTypeDeleteDialogComponent } from './user-type-delete-dialog.component';
import { userTypeRoute } from './user-type.route';

@NgModule({
  imports: [CardoholicSharedModule, RouterModule.forChild(userTypeRoute)],
  declarations: [UserTypeComponent, UserTypeDetailComponent, UserTypeUpdateComponent, UserTypeDeleteDialogComponent],
  entryComponents: [UserTypeDeleteDialogComponent],
})
export class CardoholicUserTypeModule {}
