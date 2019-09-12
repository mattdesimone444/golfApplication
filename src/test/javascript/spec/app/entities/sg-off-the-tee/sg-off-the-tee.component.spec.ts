/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGOffTheTeeComponent } from 'app/entities/sg-off-the-tee/sg-off-the-tee.component';
import { SGOffTheTeeService } from 'app/entities/sg-off-the-tee/sg-off-the-tee.service';
import { SGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

describe('Component Tests', () => {
  describe('SGOffTheTee Management Component', () => {
    let comp: SGOffTheTeeComponent;
    let fixture: ComponentFixture<SGOffTheTeeComponent>;
    let service: SGOffTheTeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGOffTheTeeComponent],
        providers: []
      })
        .overrideTemplate(SGOffTheTeeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGOffTheTeeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGOffTheTeeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SGOffTheTee(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sGOffTheTees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
