/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { DistanceOfApproachDeleteDialogComponent } from 'app/entities/distance-of-approach/distance-of-approach-delete-dialog.component';
import { DistanceOfApproachService } from 'app/entities/distance-of-approach/distance-of-approach.service';

describe('Component Tests', () => {
  describe('DistanceOfApproach Management Delete Component', () => {
    let comp: DistanceOfApproachDeleteDialogComponent;
    let fixture: ComponentFixture<DistanceOfApproachDeleteDialogComponent>;
    let service: DistanceOfApproachService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DistanceOfApproachDeleteDialogComponent]
      })
        .overrideTemplate(DistanceOfApproachDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DistanceOfApproachDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceOfApproachService);
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
