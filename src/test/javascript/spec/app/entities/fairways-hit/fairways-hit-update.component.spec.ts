/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { FairwaysHitUpdateComponent } from 'app/entities/fairways-hit/fairways-hit-update.component';
import { FairwaysHitService } from 'app/entities/fairways-hit/fairways-hit.service';
import { FairwaysHit } from 'app/shared/model/fairways-hit.model';

describe('Component Tests', () => {
  describe('FairwaysHit Management Update Component', () => {
    let comp: FairwaysHitUpdateComponent;
    let fixture: ComponentFixture<FairwaysHitUpdateComponent>;
    let service: FairwaysHitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [FairwaysHitUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FairwaysHitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FairwaysHitUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FairwaysHitService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FairwaysHit(123);
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
        const entity = new FairwaysHit();
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
