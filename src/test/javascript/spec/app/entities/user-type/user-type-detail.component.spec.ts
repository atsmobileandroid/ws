import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardoholicTestModule } from '../../../test.module';
import { UserTypeDetailComponent } from 'app/entities/user-type/user-type-detail.component';
import { UserType } from 'app/shared/model/user-type.model';

describe('Component Tests', () => {
  describe('UserType Management Detail Component', () => {
    let comp: UserTypeDetailComponent;
    let fixture: ComponentFixture<UserTypeDetailComponent>;
    const route = ({ data: of({ userType: new UserType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [UserTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
