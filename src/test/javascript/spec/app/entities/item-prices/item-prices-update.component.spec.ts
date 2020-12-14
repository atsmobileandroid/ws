import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { ItemPricesUpdateComponent } from 'app/entities/item-prices/item-prices-update.component';
import { ItemPricesService } from 'app/entities/item-prices/item-prices.service';
import { ItemPrices } from 'app/shared/model/item-prices.model';

describe('Component Tests', () => {
  describe('ItemPrices Management Update Component', () => {
    let comp: ItemPricesUpdateComponent;
    let fixture: ComponentFixture<ItemPricesUpdateComponent>;
    let service: ItemPricesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [ItemPricesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ItemPricesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ItemPricesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemPricesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ItemPrices(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ItemPrices();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
