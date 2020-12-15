import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { ItemCodesDetailComponent } from 'app/entities/item-codes/item-codes-detail.component';
import { ItemCodes } from 'app/shared/model/item-codes.model';

describe('Component Tests', () => {
  describe('ItemCodes Management Detail Component', () => {
    let comp: ItemCodesDetailComponent;
    let fixture: ComponentFixture<ItemCodesDetailComponent>;
    const route = ({ data: of({ itemCodes: new ItemCodes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [ItemCodesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ItemCodesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ItemCodesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load itemCodes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.itemCodes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
