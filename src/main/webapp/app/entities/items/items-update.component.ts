import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IItems, Items } from 'app/shared/model/items.model';
import { ItemsService } from './items.service';
import { IItemPrices } from 'app/shared/model/item-prices.model';
import { ItemPricesService } from 'app/entities/item-prices/item-prices.service';
import { IBasket } from 'app/shared/model/basket.model';
import { BasketService } from 'app/entities/basket/basket.service';

type SelectableEntity = IItemPrices | IBasket;

@Component({
  selector: 'jhi-items-update',
  templateUrl: './items-update.component.html',
})
export class ItemsUpdateComponent implements OnInit {
  isSaving = false;
  itemprices: IItemPrices[] = [];
  baskets: IBasket[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    description: [],
    imagePath: [],
    isLeaf: [],
    itemPricesId: [],
    basketId: [],
  });

  constructor(
    protected itemsService: ItemsService,
    protected itemPricesService: ItemPricesService,
    protected basketService: BasketService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ items }) => {
      this.updateForm(items);

      this.itemPricesService
        .query({ filter: 'items-is-null' })
        .pipe(
          map((res: HttpResponse<IItemPrices[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IItemPrices[]) => {
          if (!items.itemPricesId) {
            this.itemprices = resBody;
          } else {
            this.itemPricesService
              .find(items.itemPricesId)
              .pipe(
                map((subRes: HttpResponse<IItemPrices>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IItemPrices[]) => (this.itemprices = concatRes));
          }
        });

      this.basketService.query().subscribe((res: HttpResponse<IBasket[]>) => (this.baskets = res.body || []));
    });
  }

  updateForm(items: IItems): void {
    this.editForm.patchValue({
      id: items.id,
      title: items.title,
      description: items.description,
      imagePath: items.imagePath,
      isLeaf: items.isLeaf,
      itemPricesId: items.itemPricesId,
      basketId: items.basketId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const items = this.createFromForm();
    if (items.id !== undefined) {
      this.subscribeToSaveResponse(this.itemsService.update(items));
    } else {
      this.subscribeToSaveResponse(this.itemsService.create(items));
    }
  }

  private createFromForm(): IItems {
    return {
      ...new Items(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      imagePath: this.editForm.get(['imagePath'])!.value,
      isLeaf: this.editForm.get(['isLeaf'])!.value,
      itemPricesId: this.editForm.get(['itemPricesId'])!.value,
      basketId: this.editForm.get(['basketId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItems>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
