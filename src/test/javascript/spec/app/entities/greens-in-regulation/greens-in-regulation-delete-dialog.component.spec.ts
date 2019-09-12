/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { GreensInRegulationDeleteDialogComponent } from 'app/entities/greens-in-regulation/greens-in-regulation-delete-dialog.component';
import { GreensInRegulationService } from 'app/entities/greens-in-regulation/greens-in-regulation.service';

describe('Component Tests', () => {
  describe('GreensInRegulation Management Delete Component', () => {
    let comp: GreensInRegulationDeleteDialogComponent;
    let fixture: ComponentFixture<GreensInRegulationDeleteDialogComponent>;
    let service: GreensInRegulationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GreensInRegulationDeleteDialogComponent]
      })
        .overrideTemplate(GreensInRegulationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GreensInRegulationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GreensInRegulationService);
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
