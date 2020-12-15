import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IItemPrices } from 'app/shared/model/item-prices.model';
import { ItemPricesService } from './item-prices.service';
import { ItemPricesDeleteDialogComponent } from './item-prices-delete-dialog.component';

@Component({
  selector: 'jhi-item-prices',
  templateUrl: './item-prices.component.html',
})
export class ItemPricesComponent implements OnInit, OnDestroy {
  itemPrices?: IItemPrices[];
  eventSubscriber?: Subscription;

  constructor(protected itemPricesService: ItemPricesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.itemPricesService.query().subscribe((res: HttpResponse<IItemPrices[]>) => (this.itemPrices = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInItemPrices();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IItemPrices): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInItemPrices(): void {
    this.eventSubscriber = this.eventManager.subscribe('itemPricesListModification', () => this.loadAll());
  }

  delete(itemPrices: IItemPrices): void {
    const modalRef = this.modalService.open(ItemPricesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.itemPrices = itemPrices;
  }
}
