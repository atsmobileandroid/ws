import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserType, UserType } from 'app/shared/model/user-type.model';
import { UserTypeService } from './user-type.service';

@Component({
  selector: 'jhi-user-type-update',
  templateUrl: './user-type-update.component.html',
})
export class UserTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(protected userTypeService: UserTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userType }) => {
      this.updateForm(userType);
    });
  }

  updateForm(userType: IUserType): void {
    this.editForm.patchValue({
      id: userType.id,
      name: userType.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userType = this.createFromForm();
    if (userType.id !== undefined) {
      this.subscribeToSaveResponse(this.userTypeService.update(userType));
    } else {
      this.subscribeToSaveResponse(this.userTypeService.create(userType));
    }
  }

  private createFromForm(): IUserType {
    return {
      ...new UserType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserType>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
