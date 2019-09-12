/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGArroundTheGreenUpdateComponent } from 'app/entities/sg-arround-the-green/sg-arround-the-green-update.component';
import { SGArroundTheGreenService } from 'app/entities/sg-arround-the-green/sg-arround-the-green.service';
import { SGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';

describe('Component Tests', () => {
  describe('SGArroundTheGreen Management Update Component', () => {
    let comp: SGArroundTheGreenUpdateComponent;
    let fixture: ComponentFixture<SGArroundTheGreenUpdateComponent>;
    let service: SGArroundTheGreenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGArroundTheGreenUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SGArroundTheGreenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGArroundTheGreenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGArroundTheGreenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SGArroundTheGreen(123);
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
        const entity = new SGArroundTheGreen();
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
