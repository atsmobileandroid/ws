import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { LevelsUpdateComponent } from 'app/entities/levels/levels-update.component';
import { LevelsService } from 'app/entities/levels/levels.service';
import { Levels } from 'app/shared/model/levels.model';

describe('Component Tests', () => {
  describe('Levels Management Update Component', () => {
    let comp: LevelsUpdateComponent;
    let fixture: ComponentFixture<LevelsUpdateComponent>;
    let service: LevelsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [LevelsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LevelsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LevelsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LevelsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Levels(123);
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
        const entity = new Levels();
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
