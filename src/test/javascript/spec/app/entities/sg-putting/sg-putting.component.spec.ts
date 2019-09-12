/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGPuttingComponent } from 'app/entities/sg-putting/sg-putting.component';
import { SGPuttingService } from 'app/entities/sg-putting/sg-putting.service';
import { SGPutting } from 'app/shared/model/sg-putting.model';

describe('Component Tests', () => {
  describe('SGPutting Management Component', () => {
    let comp: SGPuttingComponent;
    let fixture: ComponentFixture<SGPuttingComponent>;
    let service: SGPuttingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGPuttingComponent],
        providers: []
      })
        .overrideTemplate(SGPuttingComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGPuttingComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGPuttingService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SGPutting(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sGPuttings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
