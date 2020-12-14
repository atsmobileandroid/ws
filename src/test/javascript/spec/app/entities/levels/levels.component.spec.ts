import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CardoholicTestModule } from '../../../test.module';
import { LevelsComponent } from 'app/entities/levels/levels.component';
import { LevelsService } from 'app/entities/levels/levels.service';
import { Levels } from 'app/shared/model/levels.model';

describe('Component Tests', () => {
  describe('Levels Management Component', () => {
    let comp: LevelsComponent;
    let fixture: ComponentFixture<LevelsComponent>;
    let service: LevelsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [LevelsComponent],
      })
        .overrideTemplate(LevelsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LevelsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LevelsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Levels(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.levels && comp.levels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
