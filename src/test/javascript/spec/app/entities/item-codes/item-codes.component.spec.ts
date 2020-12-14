import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CardoholicTestModule } from '../../../test.module';
import { ItemCodesComponent } from 'app/entities/item-codes/item-codes.component';
import { ItemCodesService } from 'app/entities/item-codes/item-codes.service';
import { ItemCodes } from 'app/shared/model/item-codes.model';

describe('Component Tests', () => {
  describe('ItemCodes Management Component', () => {
    let comp: ItemCodesComponent;
    let fixture: ComponentFixture<ItemCodesComponent>;
    let service: ItemCodesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardoholicTestModule],
        declarations: [ItemCodesComponent],
      })
        .overrideTemplate(ItemCodesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ItemCodesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemCodesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ItemCodes(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.itemCodes && comp.itemCodes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
