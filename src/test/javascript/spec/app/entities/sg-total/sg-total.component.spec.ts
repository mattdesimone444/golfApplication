/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGTotalComponent } from 'app/entities/sg-total/sg-total.component';
import { SGTotalService } from 'app/entities/sg-total/sg-total.service';
import { SGTotal } from 'app/shared/model/sg-total.model';

describe('Component Tests', () => {
  describe('SGTotal Management Component', () => {
    let comp: SGTotalComponent;
    let fixture: ComponentFixture<SGTotalComponent>;
    let service: SGTotalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGTotalComponent],
        providers: []
      })
        .overrideTemplate(SGTotalComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGTotalComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGTotalService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SGTotal(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sGTotals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
