import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemCodes } from 'app/shared/model/item-codes.model';

@Component({
  selector: 'jhi-item-codes-detail',
  templateUrl: './item-codes-detail.component.html',
})
export class ItemCodesDetailComponent implements OnInit {
  itemCodes: IItemCodes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ itemCodes }) => (this.itemCodes = itemCodes));
  }

  previousState(): void {
    window.history.back();
  }
}
