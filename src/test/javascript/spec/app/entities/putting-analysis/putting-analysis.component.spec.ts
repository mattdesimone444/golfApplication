/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttingAnalysisComponent } from 'app/entities/putting-analysis/putting-analysis.component';
import { PuttingAnalysisService } from 'app/entities/putting-analysis/putting-analysis.service';
import { PuttingAnalysis } from 'app/shared/model/putting-analysis.model';

describe('Component Tests', () => {
  describe('PuttingAnalysis Management Component', () => {
    let comp: PuttingAnalysisComponent;
    let fixture: ComponentFixture<PuttingAnalysisComponent>;
    let service: PuttingAnalysisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttingAnalysisComponent],
        providers: []
      })
        .overrideTemplate(PuttingAnalysisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuttingAnalysisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttingAnalysisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PuttingAnalysis(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.puttingAnalyses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
