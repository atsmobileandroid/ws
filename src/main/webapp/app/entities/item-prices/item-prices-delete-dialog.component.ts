import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemPrices } from 'app/shared/model/item-prices.model';
import { ItemPricesService } from './item-prices.service';

@Component({
  templateUrl: './item-prices-delete-dialog.component.html',
})
export class ItemPricesDeleteDialogComponent {
  itemPrices?: IItemPrices;

  constructor(
    protected itemPricesService: ItemPricesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.itemPricesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('itemPricesListModification');
      this.activeModal.close();
    });
  }
}
