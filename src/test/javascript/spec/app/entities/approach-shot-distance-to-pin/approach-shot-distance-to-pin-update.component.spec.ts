/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { ApproachShotDistanceToPinUpdateComponent } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin-update.component';
import { ApproachShotDistanceToPinService } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin.service';
import { ApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';

describe('Component Tests', () => {
  describe('ApproachShotDistanceToPin Management Update Component', () => {
    let comp: ApproachShotDistanceToPinUpdateComponent;
    let fixture: ComponentFixture<ApproachShotDistanceToPinUpdateComponent>;
    let service: ApproachShotDistanceToPinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [ApproachShotDistanceToPinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApproachShotDistanceToPinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApproachShotDistanceToPinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApproachShotDistanceToPinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApproachShotDistanceToPin(123);
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
        const entity = new ApproachShotDistanceToPin();
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
