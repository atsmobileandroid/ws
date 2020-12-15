import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrderItems } from 'app/shared/model/order-items.model';
import { OrderItemsService } from './order-items.service';
import { OrderItemsDeleteDialogComponent } from './order-items-delete-dialog.component';

@Component({
  selector: 'jhi-order-items',
  templateUrl: './order-items.component.html',
})
export class OrderItemsComponent implements OnInit, OnDestroy {
  orderItems?: IOrderItems[];
  eventSubscriber?: Subscription;

  constructor(protected orderItemsService: OrderItemsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.orderItemsService.query().subscribe((res: HttpResponse<IOrderItems[]>) => (this.orderItems = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrderItems();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrderItems): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrderItems(): void {
    this.eventSubscriber = this.eventManager.subscribe('orderItemsListModification', () => this.loadAll());
  }

  delete(orderItems: IOrderItems): void {
    const modalRef = this.modalService.open(OrderItemsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.orderItems = orderItems;
  }
}
