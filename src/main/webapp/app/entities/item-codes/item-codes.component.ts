import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IItemCodes } from 'app/shared/model/item-codes.model';
import { ItemCodesService } from './item-codes.service';
import { ItemCodesDeleteDialogComponent } from './item-codes-delete-dialog.component';

@Component({
  selector: 'jhi-item-codes',
  templateUrl: './item-codes.component.html',
})
export class ItemCodesComponent implements OnInit, OnDestroy {
  itemCodes?: IItemCodes[];
  eventSubscriber?: Subscription;

  constructor(protected itemCodesService: ItemCodesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.itemCodesService.query().subscribe((res: HttpResponse<IItemCodes[]>) => (this.itemCodes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInItemCodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IItemCodes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInItemCodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('itemCodesListModification', () => this.loadAll());
  }

  delete(itemCodes: IItemCodes): void {
    const modalRef = this.modalService.open(ItemCodesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.itemCodes = itemCodes;
  }
}
