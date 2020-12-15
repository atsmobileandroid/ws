import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CardoholicTestModule } from '../../../test.module';
import { UserTypeComponent } from 'app/entities/user-type/user-type.component';
import { UserTypeService } from 'app/entities/user-type/user-type.service';
import { UserType } from 'app/shared/model/user-type.model';

describe('Component Tests', () => {
  describe('UserType Management Component', () => {
    let comp: UserTypeComponent;
    let fixture: ComponentFixture<UserTypeComponent>;
    let service: UserTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [UserTypeComponent],
      })
        .overrideTemplate(UserTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserType(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userTypes && comp.userTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
