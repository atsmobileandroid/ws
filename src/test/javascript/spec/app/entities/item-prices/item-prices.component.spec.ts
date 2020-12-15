import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CardoholicTestModule } from '../../../test.module';
import { ItemPricesComponent } from 'app/entities/item-prices/item-prices.component';
import { ItemPricesService } from 'app/entities/item-prices/item-prices.service';
import { ItemPrices } from 'app/shared/model/item-prices.model';

describe('Component Tests', () => {
  describe('ItemPrices Management Component', () => {
    let comp: ItemPricesComponent;
    let fixture: ComponentFixture<ItemPricesComponent>;
    let service: ItemPricesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [ItemPricesComponent],
      })
        .overrideTemplate(ItemPricesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ItemPricesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemPricesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ItemPrices(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.itemPrices && comp.itemPrices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
