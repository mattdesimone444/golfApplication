/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { GreensInRegulationDetailComponent } from 'app/entities/greens-in-regulation/greens-in-regulation-detail.component';
import { GreensInRegulation } from 'app/shared/model/greens-in-regulation.model';

describe('Component Tests', () => {
  describe('GreensInRegulation Management Detail Component', () => {
    let comp: GreensInRegulationDetailComponent;
    let fixture: ComponentFixture<GreensInRegulationDetailComponent>;
    const route = ({ data: of({ greensInRegulation: new GreensInRegulation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GreensInRegulationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GreensInRegulationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GreensInRegulationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.greensInRegulation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
