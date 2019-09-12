/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { GreensInRegulationComponent } from 'app/entities/greens-in-regulation/greens-in-regulation.component';
import { GreensInRegulationService } from 'app/entities/greens-in-regulation/greens-in-regulation.service';
import { GreensInRegulation } from 'app/shared/model/greens-in-regulation.model';

describe('Component Tests', () => {
  describe('GreensInRegulation Management Component', () => {
    let comp: GreensInRegulationComponent;
    let fixture: ComponentFixture<GreensInRegulationComponent>;
    let service: GreensInRegulationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GreensInRegulationComponent],
        providers: []
      })
        .overrideTemplate(GreensInRegulationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GreensInRegulationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GreensInRegulationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GreensInRegulation(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.greensInRegulations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
