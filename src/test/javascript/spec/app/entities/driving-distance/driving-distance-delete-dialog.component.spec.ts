/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { DrivingDistanceDeleteDialogComponent } from 'app/entities/driving-distance/driving-distance-delete-dialog.component';
import { DrivingDistanceService } from 'app/entities/driving-distance/driving-distance.service';

describe('Component Tests', () => {
  describe('DrivingDistance Management Delete Component', () => {
    let comp: DrivingDistanceDeleteDialogComponent;
    let fixture: ComponentFixture<DrivingDistanceDeleteDialogComponent>;
    let service: DrivingDistanceService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DrivingDistanceDeleteDialogComponent]
      })
        .overrideTemplate(DrivingDistanceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DrivingDistanceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DrivingDistanceService);
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
