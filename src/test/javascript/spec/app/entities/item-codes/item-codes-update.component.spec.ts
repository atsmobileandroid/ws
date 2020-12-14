import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { ItemCodesUpdateComponent } from 'app/entities/item-codes/item-codes-update.component';
import { ItemCodesService } from 'app/entities/item-codes/item-codes.service';
import { ItemCodes } from 'app/shared/model/item-codes.model';

describe('Component Tests', () => {
  describe('ItemCodes Management Update Component', () => {
    let comp: ItemCodesUpdateComponent;
    let fixture: ComponentFixture<ItemCodesUpdateComponent>;
    let service: ItemCodesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [ItemCodesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ItemCodesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ItemCodesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemCodesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ItemCodes(123);
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
        const entity = new ItemCodes();
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
