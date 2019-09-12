/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { GolferUpdateComponent } from 'app/entities/golfer/golfer-update.component';
import { GolferService } from 'app/entities/golfer/golfer.service';
import { Golfer } from 'app/shared/model/golfer.model';

describe('Component Tests', () => {
  describe('Golfer Management Update Component', () => {
    let comp: GolferUpdateComponent;
    let fixture: ComponentFixture<GolferUpdateComponent>;
    let service: GolferService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GolferUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GolferUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GolferUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GolferService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Golfer(123);
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
        const entity = new Golfer();
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
