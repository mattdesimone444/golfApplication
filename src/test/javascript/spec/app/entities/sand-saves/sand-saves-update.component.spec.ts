/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SandSavesUpdateComponent } from 'app/entities/sand-saves/sand-saves-update.component';
import { SandSavesService } from 'app/entities/sand-saves/sand-saves.service';
import { SandSaves } from 'app/shared/model/sand-saves.model';

describe('Component Tests', () => {
  describe('SandSaves Management Update Component', () => {
    let comp: SandSavesUpdateComponent;
    let fixture: ComponentFixture<SandSavesUpdateComponent>;
    let service: SandSavesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SandSavesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SandSavesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SandSavesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SandSavesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SandSaves(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SandSaves();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
