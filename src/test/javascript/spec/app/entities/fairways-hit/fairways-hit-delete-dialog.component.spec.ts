/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { FairwaysHitDeleteDialogComponent } from 'app/entities/fairways-hit/fairways-hit-delete-dialog.component';
import { FairwaysHitService } from 'app/entities/fairways-hit/fairways-hit.service';

describe('Component Tests', () => {
  describe('FairwaysHit Management Delete Component', () => {
    let comp: FairwaysHitDeleteDialogComponent;
    let fixture: ComponentFixture<FairwaysHitDeleteDialogComponent>;
    let service: FairwaysHitService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [FairwaysHitDeleteDialogComponent]
      })
        .overrideTemplate(FairwaysHitDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FairwaysHitDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FairwaysHitService);
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
