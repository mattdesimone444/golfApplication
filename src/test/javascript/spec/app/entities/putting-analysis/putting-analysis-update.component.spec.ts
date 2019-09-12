/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttingAnalysisUpdateComponent } from 'app/entities/putting-analysis/putting-analysis-update.component';
import { PuttingAnalysisService } from 'app/entities/putting-analysis/putting-analysis.service';
import { PuttingAnalysis } from 'app/shared/model/putting-analysis.model';

describe('Component Tests', () => {
  describe('PuttingAnalysis Management Update Component', () => {
    let comp: PuttingAnalysisUpdateComponent;
    let fixture: ComponentFixture<PuttingAnalysisUpdateComponent>;
    let service: PuttingAnalysisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttingAnalysisUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PuttingAnalysisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuttingAnalysisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttingAnalysisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PuttingAnalysis(123);
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
        const entity = new PuttingAnalysis();
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
