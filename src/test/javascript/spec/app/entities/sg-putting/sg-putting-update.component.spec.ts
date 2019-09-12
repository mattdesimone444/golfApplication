/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGPuttingUpdateComponent } from 'app/entities/sg-putting/sg-putting-update.component';
import { SGPuttingService } from 'app/entities/sg-putting/sg-putting.service';
import { SGPutting } from 'app/shared/model/sg-putting.model';

describe('Component Tests', () => {
  describe('SGPutting Management Update Component', () => {
    let comp: SGPuttingUpdateComponent;
    let fixture: ComponentFixture<SGPuttingUpdateComponent>;
    let service: SGPuttingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGPuttingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SGPuttingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGPuttingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGPuttingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SGPutting(123);
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
        const entity = new SGPutting();
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
