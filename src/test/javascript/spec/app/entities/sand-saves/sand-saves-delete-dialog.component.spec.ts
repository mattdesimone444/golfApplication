/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GolfApplicationTestModule } from '../../../test.module';
import { SandSavesDeleteDialogComponent } from 'app/entities/sand-saves/sand-saves-delete-dialog.component';
import { SandSavesService } from 'app/entities/sand-saves/sand-saves.service';

describe('Component Tests', () => {
  describe('SandSaves Management Delete Component', () => {
    let comp: SandSavesDeleteDialogComponent;
    let fixture: ComponentFixture<SandSavesDeleteDialogComponent>;
    let service: SandSavesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SandSavesDeleteDialogComponent]
      })
        .overrideTemplate(SandSavesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SandSavesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SandSavesService);
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
