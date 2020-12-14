import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderItems } from 'app/shared/model/order-items.model';
import { OrderItemsService } from './order-items.service';

@Component({
  templateUrl: './order-items-delete-dialog.component.html',
})
export class OrderItemsDeleteDialogComponent {
  orderItems?: IOrderItems;

  constructor(
    protected orderItemsService: OrderItemsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.orderItemsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('orderItemsListModification');
      this.activeModal.close();
    });
  }
}
