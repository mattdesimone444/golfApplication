/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { TournamentPuttingAnalysisUpdateComponent } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis-update.component';
import { TournamentPuttingAnalysisService } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis.service';
import { TournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';

describe('Component Tests', () => {
  describe('TournamentPuttingAnalysis Management Update Component', () => {
    let comp: TournamentPuttingAnalysisUpdateComponent;
    let fixture: ComponentFixture<TournamentPuttingAnalysisUpdateComponent>;
    let service: TournamentPuttingAnalysisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [TournamentPuttingAnalysisUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TournamentPuttingAnalysisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TournamentPuttingAnalysisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TournamentPuttingAnalysisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TournamentPuttingAnalysis(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TournamentPuttingAnalysis();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
