/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGApproachUpdateComponent } from 'app/entities/sg-approach/sg-approach-update.component';
import { SGApproachService } from 'app/entities/sg-approach/sg-approach.service';
import { SGApproach } from 'app/shared/model/sg-approach.model';

describe('Component Tests', () => {
  describe('SGApproach Management Update Component', () => {
    let comp: SGApproachUpdateComponent;
    let fixture: ComponentFixture<SGApproachUpdateComponent>;
    let service: SGApproachService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGApproachUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SGApproachUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGApproachUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGApproachService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SGApproach(123);
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
        const entity = new SGApproach();
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
