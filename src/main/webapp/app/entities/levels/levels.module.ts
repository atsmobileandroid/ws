import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardoholicSharedModule } from 'app/shared/shared.module';
import { LevelsComponent } from './levels.component';
import { LevelsDetailComponent } from './levels-detail.component';
import { LevelsUpdateComponent } from './levels-update.component';
import { LevelsDeleteDialogComponent } from './levels-delete-dialog.component';
import { levelsRoute } from './levels.route';

@NgModule({
  imports: [CardoholicSharedModule, RouterModule.forChild(levelsRoute)],
  declarations: [LevelsComponent, LevelsDetailComponent, LevelsUpdateComponent, LevelsDeleteDialogComponent],
  entryComponents: [LevelsDeleteDialogComponent],
})
export class CardoholicLevelsModule {}
