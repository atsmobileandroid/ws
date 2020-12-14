import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IItems } from 'app/shared/model/items.model';
import { ItemsService } from './items.service';
import { ItemsDeleteDialogComponent } from './items-delete-dialog.component';

@Component({
  selector: 'jhi-items',
  templateUrl: './items.component.html',
})
export class ItemsComponent implements OnInit, OnDestroy {
  items?: IItems[];
  eventSubscriber?: Subscription;

  constructor(protected itemsService: ItemsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.itemsService.query().subscribe((res: HttpResponse<IItems[]>) => (this.items = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInItems();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IItems): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInItems(): void {
    this.eventSubscriber = this.eventManager.subscribe('itemsListModification', () => this.loadAll());
  }

  delete(items: IItems): void {
    const modalRef = this.modalService.open(ItemsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.items = items;
  }
}
