import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemPrices } from 'app/shared/model/item-prices.model';

@Component({
  selector: 'jhi-item-prices-detail',
  templateUrl: './item-prices-detail.component.html',
})
export class ItemPricesDetailComponent implements OnInit {
  itemPrices: IItemPrices | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ itemPrices }) => (this.itemPrices = itemPrices));
  }

  previousState(): void {
    window.history.back();
  }
}
