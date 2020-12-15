import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevels } from 'app/shared/model/levels.model';
import { LevelsService } from './levels.service';

@Component({
  templateUrl: './levels-delete-dialog.component.html',
})
export class LevelsDeleteDialogComponent {
  levels?: ILevels;

  constructor(protected levelsService: LevelsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.levelsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('levelsListModification');
      this.activeModal.close();
    });
  }
}
