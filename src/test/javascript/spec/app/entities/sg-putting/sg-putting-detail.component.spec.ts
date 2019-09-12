/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGPuttingDetailComponent } from 'app/entities/sg-putting/sg-putting-detail.component';
import { SGPutting } from 'app/shared/model/sg-putting.model';

describe('Component Tests', () => {
  describe('SGPutting Management Detail Component', () => {
    let comp: SGPuttingDetailComponent;
    let fixture: ComponentFixture<SGPuttingDetailComponent>;
    const route = ({ data: of({ sGPutting: new SGPutting(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGPuttingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SGPuttingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGPuttingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sGPutting).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
