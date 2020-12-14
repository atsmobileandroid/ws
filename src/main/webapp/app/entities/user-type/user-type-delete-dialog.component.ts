import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserType } from 'app/shared/model/user-type.model';
import { UserTypeService } from './user-type.service';

@Component({
  templateUrl: './user-type-delete-dialog.component.html',
})
export class UserTypeDeleteDialogComponent {
  userType?: IUserType;

  constructor(protected userTypeService: UserTypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userTypeListModification');
      this.activeModal.close();
    });
  }
}
