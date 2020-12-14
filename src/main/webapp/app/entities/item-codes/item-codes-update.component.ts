import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IItemCodes, ItemCodes } from 'app/shared/model/item-codes.model';
import { ItemCodesService } from './item-codes.service';
import { IItems } from 'app/shared/model/items.model';
import { ItemsService } from 'app/entities/items/items.service';

@Component({
  selector: 'jhi-item-codes-update',
  templateUrl: './item-codes-update.component.html',
})
export class ItemCodesUpdateComponent implements OnInit {
  isSaving = false;
  items: IItems[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    itemsId: [],
  });

  constructor(
    protected itemCodesService: ItemCodesService,
    protected itemsService: ItemsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ itemCodes }) => {
      this.updateForm(itemCodes);

      this.itemsService.query().subscribe((res: HttpResponse<IItems[]>) => (this.items = res.body || []));
    });
  }

  updateForm(itemCodes: IItemCodes): void {
    this.editForm.patchValue({
      id: itemCodes.id,
      code: itemCodes.code,
      itemsId: itemCodes.itemsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const itemCodes = this.createFromForm();
    if (itemCodes.id !== undefined) {
      this.subscribeToSaveResponse(this.itemCodesService.update(itemCodes));
    } else {
      this.subscribeToSaveResponse(this.itemCodesService.create(itemCodes));
    }
  }

  private createFromForm(): IItemCodes {
    return {
      ...new ItemCodes(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      itemsId: this.editForm.get(['itemsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItemCodes>>): void {
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

  trackById(index: number, item: IItems): any {
    return item.id;
  }
}
