import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { OrderItemsDetailComponent } from 'app/entities/order-items/order-items-detail.component';
import { OrderItems } from 'app/shared/model/order-items.model';

describe('Component Tests', () => {
  describe('OrderItems Management Detail Component', () => {
    let comp: OrderItemsDetailComponent;
    let fixture: ComponentFixture<OrderItemsDetailComponent>;
    const route = ({ data: of({ orderItems: new OrderItems(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [OrderItemsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OrderItemsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderItemsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load orderItems on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderItems).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
