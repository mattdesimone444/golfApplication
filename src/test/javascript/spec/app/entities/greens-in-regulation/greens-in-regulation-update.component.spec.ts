/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { GreensInRegulationUpdateComponent } from 'app/entities/greens-in-regulation/greens-in-regulation-update.component';
import { GreensInRegulationService } from 'app/entities/greens-in-regulation/greens-in-regulation.service';
import { GreensInRegulation } from 'app/shared/model/greens-in-regulation.model';

describe('Component Tests', () => {
  describe('GreensInRegulation Management Update Component', () => {
    let comp: GreensInRegulationUpdateComponent;
    let fixture: ComponentFixture<GreensInRegulationUpdateComponent>;
    let service: GreensInRegulationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GreensInRegulationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GreensInRegulationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GreensInRegulationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GreensInRegulationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GreensInRegulation(123);
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
        const entity = new GreensInRegulation();
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
