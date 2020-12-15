import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderItems } from 'app/shared/model/order-items.model';

@Component({
  selector: 'jhi-order-items-detail',
  templateUrl: './order-items-detail.component.html',
})
export class OrderItemsDetailComponent implements OnInit {
  orderItems: IOrderItems | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderItems }) => (this.orderItems = orderItems));
  }

  previousState(): void {
    window.history.back();
  }
}
