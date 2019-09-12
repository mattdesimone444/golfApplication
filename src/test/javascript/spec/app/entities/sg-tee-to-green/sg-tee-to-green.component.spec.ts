/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGTeeToGreenComponent } from 'app/entities/sg-tee-to-green/sg-tee-to-green.component';
import { SGTeeToGreenService } from 'app/entities/sg-tee-to-green/sg-tee-to-green.service';
import { SGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';

describe('Component Tests', () => {
  describe('SGTeeToGreen Management Component', () => {
    let comp: SGTeeToGreenComponent;
    let fixture: ComponentFixture<SGTeeToGreenComponent>;
    let service: SGTeeToGreenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGTeeToGreenComponent],
        providers: []
      })
        .overrideTemplate(SGTeeToGreenComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGTeeToGreenComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGTeeToGreenService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SGTeeToGreen(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sGTeeToGreens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
