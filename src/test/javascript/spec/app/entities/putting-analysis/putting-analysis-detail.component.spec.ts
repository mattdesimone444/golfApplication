/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttingAnalysisDetailComponent } from 'app/entities/putting-analysis/putting-analysis-detail.component';
import { PuttingAnalysis } from 'app/shared/model/putting-analysis.model';

describe('Component Tests', () => {
  describe('PuttingAnalysis Management Detail Component', () => {
    let comp: PuttingAnalysisDetailComponent;
    let fixture: ComponentFixture<PuttingAnalysisDetailComponent>;
    const route = ({ data: of({ puttingAnalysis: new PuttingAnalysis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttingAnalysisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PuttingAnalysisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuttingAnalysisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.puttingAnalysis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
