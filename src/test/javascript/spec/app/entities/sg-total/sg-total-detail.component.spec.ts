/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGTotalDetailComponent } from 'app/entities/sg-total/sg-total-detail.component';
import { SGTotal } from 'app/shared/model/sg-total.model';

describe('Component Tests', () => {
  describe('SGTotal Management Detail Component', () => {
    let comp: SGTotalDetailComponent;
    let fixture: ComponentFixture<SGTotalDetailComponent>;
    const route = ({ data: of({ sGTotal: new SGTotal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGTotalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SGTotalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGTotalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sGTotal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
