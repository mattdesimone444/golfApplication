/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGTotalDeleteDialogComponent } from 'app/entities/sg-total/sg-total-delete-dialog.component';
import { SGTotalService } from 'app/entities/sg-total/sg-total.service';

describe('Component Tests', () => {
  describe('SGTotal Management Delete Component', () => {
    let comp: SGTotalDeleteDialogComponent;
    let fixture: ComponentFixture<SGTotalDeleteDialogComponent>;
    let service: SGTotalService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGTotalDeleteDialogComponent]
      })
        .overrideTemplate(SGTotalDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGTotalDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGTotalService);
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
