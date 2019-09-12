/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsMadeDistanceUpdateComponent } from 'app/entities/putts-made-distance/putts-made-distance-update.component';
import { PuttsMadeDistanceService } from 'app/entities/putts-made-distance/putts-made-distance.service';
import { PuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';

describe('Component Tests', () => {
  describe('PuttsMadeDistance Management Update Component', () => {
    let comp: PuttsMadeDistanceUpdateComponent;
    let fixture: ComponentFixture<PuttsMadeDistanceUpdateComponent>;
    let service: PuttsMadeDistanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsMadeDistanceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PuttsMadeDistanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuttsMadeDistanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttsMadeDistanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PuttsMadeDistance(123);
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
        const entity = new PuttsMadeDistance();
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
