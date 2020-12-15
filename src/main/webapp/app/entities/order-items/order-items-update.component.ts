import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrderItems, OrderItems } from 'app/shared/model/order-items.model';
import { OrderItemsService } from './order-items.service';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders/orders.service';

@Component({
  selector: 'jhi-order-items-update',
  templateUrl: './order-items-update.component.html',
})
export class OrderItemsUpdateComponent implements OnInit {
  isSaving = false;
  orders: IOrders[] = [];

  editForm = this.fb.group({
    id: [],
    itemTitle: [],
    itemDescription: [],
    itemImagePath: [],
    itemPrice: [],
    itemCount: [],
    itemCodes: [],
    toId: [],
    isSentToId: [],
    orders: [],
  });

  constructor(
    protected orderItemsService: OrderItemsService,
    protected ordersService: OrdersService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderItems }) => {
      this.updateForm(orderItems);

      this.ordersService.query().subscribe((res: HttpResponse<IOrders[]>) => (this.orders = res.body || []));
    });
  }

  updateForm(orderItems: IOrderItems): void {
    this.editForm.patchValue({
      id: orderItems.id,
      itemTitle: orderItems.itemTitle,
      itemDescription: orderItems.itemDescription,
      itemImagePath: orderItems.itemImagePath,
      itemPrice: orderItems.itemPrice,
      itemCount: orderItems.itemCount,
      itemCodes: orderItems.itemCodes,
      toId: orderItems.toId,
      isSentToId: orderItems.isSentToId,
      orders: orderItems.orders,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderItems = this.createFromForm();
    if (orderItems.id !== undefined) {
      this.subscribeToSaveResponse(this.orderItemsService.update(orderItems));
    } else {
      this.subscribeToSaveResponse(this.orderItemsService.create(orderItems));
    }
  }

  private createFromForm(): IOrderItems {
    return {
      ...new OrderItems(),
      id: this.editForm.get(['id'])!.value,
      itemTitle: this.editForm.get(['itemTitle'])!.value,
      itemDescription: this.editForm.get(['itemDescription'])!.value,
      itemImagePath: this.editForm.get(['itemImagePath'])!.value,
      itemPrice: this.editForm.get(['itemPrice'])!.value,
      itemCount: this.editForm.get(['itemCount'])!.value,
      itemCodes: this.editForm.get(['itemCodes'])!.value,
      toId: this.editForm.get(['toId'])!.value,
      isSentToId: this.editForm.get(['isSentToId'])!.value,
      orders: this.editForm.get(['orders'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderItems>>): void {
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

  trackById(index: number, item: IOrders): any {
    return item.id;
  }
}
