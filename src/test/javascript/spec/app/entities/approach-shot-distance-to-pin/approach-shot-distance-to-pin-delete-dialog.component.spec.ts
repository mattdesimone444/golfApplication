/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { ApproachShotDistanceToPinDeleteDialogComponent } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin-delete-dialog.component';
import { ApproachShotDistanceToPinService } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin.service';

describe('Component Tests', () => {
  describe('ApproachShotDistanceToPin Management Delete Component', () => {
    let comp: ApproachShotDistanceToPinDeleteDialogComponent;
    let fixture: ComponentFixture<ApproachShotDistanceToPinDeleteDialogComponent>;
    let service: ApproachShotDistanceToPinService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [ApproachShotDistanceToPinDeleteDialogComponent]
      })
        .overrideTemplate(ApproachShotDistanceToPinDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApproachShotDistanceToPinDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApproachShotDistanceToPinService);
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
