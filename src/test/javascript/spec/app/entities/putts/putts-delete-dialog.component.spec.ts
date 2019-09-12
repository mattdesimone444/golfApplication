/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsDeleteDialogComponent } from 'app/entities/putts/putts-delete-dialog.component';
import { PuttsService } from 'app/entities/putts/putts.service';

describe('Component Tests', () => {
  describe('Putts Management Delete Component', () => {
    let comp: PuttsDeleteDialogComponent;
    let fixture: ComponentFixture<PuttsDeleteDialogComponent>;
    let service: PuttsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsDeleteDialogComponent]
      })
        .overrideTemplate(PuttsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuttsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttsService);
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
