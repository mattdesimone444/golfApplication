/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { TournamentPuttingAnalysisDeleteDialogComponent } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis-delete-dialog.component';
import { TournamentPuttingAnalysisService } from 'app/entities/tournament-putting-analysis/tournament-putting-analysis.service';

describe('Component Tests', () => {
  describe('TournamentPuttingAnalysis Management Delete Component', () => {
    let comp: TournamentPuttingAnalysisDeleteDialogComponent;
    let fixture: ComponentFixture<TournamentPuttingAnalysisDeleteDialogComponent>;
    let service: TournamentPuttingAnalysisService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [TournamentPuttingAnalysisDeleteDialogComponent]
      })
        .overrideTemplate(TournamentPuttingAnalysisDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TournamentPuttingAnalysisDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TournamentPuttingAnalysisService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
