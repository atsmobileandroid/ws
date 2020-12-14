import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemCodes } from 'app/shared/model/item-codes.model';
import { ItemCodesService } from './item-codes.service';

@Component({
  templateUrl: './item-codes-delete-dialog.component.html',
})
export class ItemCodesDeleteDialogComponent {
  itemCodes?: IItemCodes;

  constructor(protected itemCodesService: ItemCodesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.itemCodesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('itemCodesListModification');
      this.activeModal.close();
    });
  }
}
