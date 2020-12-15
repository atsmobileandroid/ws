import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILevels } from 'app/shared/model/levels.model';
import { LevelsService } from './levels.service';
import { LevelsDeleteDialogComponent } from './levels-delete-dialog.component';

@Component({
  selector: 'jhi-levels',
  templateUrl: './levels.component.html',
})
export class LevelsComponent implements OnInit, OnDestroy {
  levels?: ILevels[];
  eventSubscriber?: Subscription;

  constructor(protected levelsService: LevelsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.levelsService.query().subscribe((res: HttpResponse<ILevels[]>) => (this.levels = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLevels();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILevels): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLevels(): void {
    this.eventSubscriber = this.eventManager.subscribe('levelsListModification', () => this.loadAll());
  }

  delete(levels: ILevels): void {
    const modalRef = this.modalService.open(LevelsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.levels = levels;
  }
}
