/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGOffTheTeeUpdateComponent } from 'app/entities/sg-off-the-tee/sg-off-the-tee-update.component';
import { SGOffTheTeeService } from 'app/entities/sg-off-the-tee/sg-off-the-tee.service';
import { SGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

describe('Component Tests', () => {
  describe('SGOffTheTee Management Update Component', () => {
    let comp: SGOffTheTeeUpdateComponent;
    let fixture: ComponentFixture<SGOffTheTeeUpdateComponent>;
    let service: SGOffTheTeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGOffTheTeeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SGOffTheTeeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGOffTheTeeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGOffTheTeeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SGOffTheTee(123);
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
        const entity = new SGOffTheTee();
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
