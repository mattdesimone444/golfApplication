/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsUpdateComponent } from 'app/entities/putts/putts-update.component';
import { PuttsService } from 'app/entities/putts/putts.service';
import { Putts } from 'app/shared/model/putts.model';

describe('Component Tests', () => {
  describe('Putts Management Update Component', () => {
    let comp: PuttsUpdateComponent;
    let fixture: ComponentFixture<PuttsUpdateComponent>;
    let service: PuttsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PuttsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuttsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Putts(123);
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
        const entity = new Putts();
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
