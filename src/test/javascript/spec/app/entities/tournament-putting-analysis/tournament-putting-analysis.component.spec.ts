/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { TournamentPuttingAnalysisComponent } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis.component';
import { TournamentPuttingAnalysisService } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis.service';
import { TournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';

describe('Component Tests', () => {
  describe('TournamentPuttingAnalysis Management Component', () => {
    let comp: TournamentPuttingAnalysisComponent;
    let fixture: ComponentFixture<TournamentPuttingAnalysisComponent>;
    let service: TournamentPuttingAnalysisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [TournamentPuttingAnalysisComponent],
        providers: []
      })
        .overrideTemplate(TournamentPuttingAnalysisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TournamentPuttingAnalysisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TournamentPuttingAnalysisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TournamentPuttingAnalysis(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tournamentPuttingAnalyses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
