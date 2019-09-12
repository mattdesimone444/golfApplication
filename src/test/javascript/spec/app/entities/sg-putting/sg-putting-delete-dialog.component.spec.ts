/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGPuttingDeleteDialogComponent } from 'app/entities/sg-putting/sg-putting-delete-dialog.component';
import { SGPuttingService } from 'app/entities/sg-putting/sg-putting.service';

describe('Component Tests', () => {
  describe('SGPutting Management Delete Component', () => {
    let comp: SGPuttingDeleteDialogComponent;
    let fixture: ComponentFixture<SGPuttingDeleteDialogComponent>;
    let service: SGPuttingService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGPuttingDeleteDialogComponent]
      })
        .overrideTemplate(SGPuttingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGPuttingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGPuttingService);
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
