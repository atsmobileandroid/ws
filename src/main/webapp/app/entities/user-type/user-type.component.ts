import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserType } from 'app/shared/model/user-type.model';
import { UserTypeService } from './user-type.service';
import { UserTypeDeleteDialogComponent } from './user-type-delete-dialog.component';

@Component({
  selector: 'jhi-user-type',
  templateUrl: './user-type.component.html',
})
export class UserTypeComponent implements OnInit, OnDestroy {
  userTypes?: IUserType[];
  eventSubscriber?: Subscription;

  constructor(protected userTypeService: UserTypeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.userTypeService.query().subscribe((res: HttpResponse<IUserType[]>) => (this.userTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('userTypeListModification', () => this.loadAll());
  }

  delete(userType: IUserType): void {
    const modalRef = this.modalService.open(UserTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userType = userType;
  }
}
