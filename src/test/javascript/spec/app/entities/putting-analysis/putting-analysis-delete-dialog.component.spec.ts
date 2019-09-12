/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttingAnalysisDeleteDialogComponent } from 'app/entities/putting-analysis/putting-analysis-delete-dialog.component';
import { PuttingAnalysisService } from 'app/entities/putting-analysis/putting-analysis.service';

describe('Component Tests', () => {
  describe('PuttingAnalysis Management Delete Component', () => {
    let comp: PuttingAnalysisDeleteDialogComponent;
    let fixture: ComponentFixture<PuttingAnalysisDeleteDialogComponent>;
    let service: PuttingAnalysisService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttingAnalysisDeleteDialogComponent]
      })
        .overrideTemplate(PuttingAnalysisDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuttingAnalysisDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttingAnalysisService);
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
