import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IItemPrices, ItemPrices } from 'app/shared/model/item-prices.model';
import { ItemPricesService } from './item-prices.service';
import { ILevels } from 'app/shared/model/levels.model';
import { LevelsService } from 'app/entities/levels/levels.service';

@Component({
  selector: 'jhi-item-prices-update',
  templateUrl: './item-prices-update.component.html',
})
export class ItemPricesUpdateComponent implements OnInit {
  isSaving = false;
  levels: ILevels[] = [];

  editForm = this.fb.group({
    id: [],
    price: [],
    levels: [],
  });

  constructor(
    protected itemPricesService: ItemPricesService,
    protected levelsService: LevelsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ itemPrices }) => {
      this.updateForm(itemPrices);

      this.levelsService
        .query({ filter: 'itemprices-is-null' })
        .pipe(
          map((res: HttpResponse<ILevels[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILevels[]) => {
          if (!itemPrices.levels || !itemPrices.levels.id) {
            this.levels = resBody;
          } else {
            this.levelsService
              .find(itemPrices.levels.id)
              .pipe(
                map((subRes: HttpResponse<ILevels>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILevels[]) => (this.levels = concatRes));
          }
        });
    });
  }

  updateForm(itemPrices: IItemPrices): void {
    this.editForm.patchValue({
      id: itemPrices.id,
      price: itemPrices.price,
      levels: itemPrices.levels,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const itemPrices = this.createFromForm();
    if (itemPrices.id !== undefined) {
      this.subscribeToSaveResponse(this.itemPricesService.update(itemPrices));
    } else {
      this.subscribeToSaveResponse(this.itemPricesService.create(itemPrices));
    }
  }

  private createFromForm(): IItemPrices {
    return {
      ...new ItemPrices(),
      id: this.editForm.get(['id'])!.value,
      price: this.editForm.get(['price'])!.value,
      levels: this.editForm.get(['levels'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItemPrices>>): void {
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

  trackById(index: number, item: ILevels): any {
    return item.id;
  }
}
