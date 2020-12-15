import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { ItemPricesDetailComponent } from 'app/entities/item-prices/item-prices-detail.component';
import { ItemPrices } from 'app/shared/model/item-prices.model';

describe('Component Tests', () => {
  describe('ItemPrices Management Detail Component', () => {
    let comp: ItemPricesDetailComponent;
    let fixture: ComponentFixture<ItemPricesDetailComponent>;
    const route = ({ data: of({ itemPrices: new ItemPrices(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [ItemPricesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ItemPricesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ItemPricesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load itemPrices on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.itemPrices).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
