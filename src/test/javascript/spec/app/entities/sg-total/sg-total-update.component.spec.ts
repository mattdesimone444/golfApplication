/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGTotalUpdateComponent } from 'app/entities/sg-total/sg-total-update.component';
import { SGTotalService } from 'app/entities/sg-total/sg-total.service';
import { SGTotal } from 'app/shared/model/sg-total.model';

describe('Component Tests', () => {
  describe('SGTotal Management Update Component', () => {
    let comp: SGTotalUpdateComponent;
    let fixture: ComponentFixture<SGTotalUpdateComponent>;
    let service: SGTotalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGTotalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SGTotalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGTotalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGTotalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SGTotal(123);
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
        const entity = new SGTotal();
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
