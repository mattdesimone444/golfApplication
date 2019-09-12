/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { TournamentPuttingAnalysisDetailComponent } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis-detail.component';
import { TournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';

describe('Component Tests', () => {
  describe('TournamentPuttingAnalysis Management Detail Component', () => {
    let comp: TournamentPuttingAnalysisDetailComponent;
    let fixture: ComponentFixture<TournamentPuttingAnalysisDetailComponent>;
    const route = ({ data: of({ tournamentPuttingAnalysis: new TournamentPuttingAnalysis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [TournamentPuttingAnalysisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TournamentPuttingAnalysisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TournamentPuttingAnalysisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tournamentPuttingAnalysis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
