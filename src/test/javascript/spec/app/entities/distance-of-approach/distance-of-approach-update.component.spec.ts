/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { DistanceOfApproachUpdateComponent } from 'app/entities/distance-of-approach/distance-of-approach-update.component';
import { DistanceOfApproachService } from 'app/entities/distance-of-approach/distance-of-approach.service';
import { DistanceOfApproach } from 'app/shared/model/distance-of-approach.model';

describe('Component Tests', () => {
  describe('DistanceOfApproach Management Update Component', () => {
    let comp: DistanceOfApproachUpdateComponent;
    let fixture: ComponentFixture<DistanceOfApproachUpdateComponent>;
    let service: DistanceOfApproachService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DistanceOfApproachUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DistanceOfApproachUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistanceOfApproachUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceOfApproachService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DistanceOfApproach(123);
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
        const entity = new DistanceOfApproach();
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
