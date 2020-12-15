import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrderItemsService } from 'app/entities/order-items/order-items.service';
import { IOrderItems, OrderItems } from 'app/shared/model/order-items.model';

describe('Service Tests', () => {
  describe('OrderItems Service', () => {
    let injector: TestBed;
    let service: OrderItemsService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrderItems;
    let expectedResult: IOrderItems | IOrderItems[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrderItemsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OrderItems(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OrderItems', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new OrderItems()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrderItems', () => {
        const returnedFromService = Object.assign(
          {
            itemTitle: 'BBBBBB',
            itemDescription: 'BBBBBB',
            itemImagePath: 'BBBBBB',
            itemPrice: 'BBBBBB',
            itemCount: 1,
            itemCodes: 'BBBBBB',
            toId: 'BBBBBB',
            isSentToId: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OrderItems', () => {
        const returnedFromService = Object.assign(
          {
            itemTitle: 'BBBBBB',
            itemDescription: 'BBBBBB',
            itemImagePath: 'BBBBBB',
            itemPrice: 'BBBBBB',
            itemCount: 1,
            itemCodes: 'BBBBBB',
            toId: 'BBBBBB',
            isSentToId: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OrderItems', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
