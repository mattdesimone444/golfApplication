/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { ScoresDeleteDialogComponent } from 'app/entities/scores/scores-delete-dialog.component';
import { ScoresService } from 'app/entities/scores/scores.service';

describe('Component Tests', () => {
  describe('Scores Management Delete Component', () => {
    let comp: ScoresDeleteDialogComponent;
    let fixture: ComponentFixture<ScoresDeleteDialogComponent>;
    let service: ScoresService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [ScoresDeleteDialogComponent]
      })
        .overrideTemplate(ScoresDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScoresDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScoresService);
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
