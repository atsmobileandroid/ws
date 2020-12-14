import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { LevelsDetailComponent } from 'app/entities/levels/levels-detail.component';
import { Levels } from 'app/shared/model/levels.model';

describe('Component Tests', () => {
  describe('Levels Management Detail Component', () => {
    let comp: LevelsDetailComponent;
    let fixture: ComponentFixture<LevelsDetailComponent>;
    const route = ({ data: of({ levels: new Levels(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [LevelsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LevelsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LevelsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load levels on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.levels).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
