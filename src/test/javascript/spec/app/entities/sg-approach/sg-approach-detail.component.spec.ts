/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGApproachDetailComponent } from 'app/entities/sg-approach/sg-approach-detail.component';
import { SGApproach } from 'app/shared/model/sg-approach.model';

describe('Component Tests', () => {
  describe('SGApproach Management Detail Component', () => {
    let comp: SGApproachDetailComponent;
    let fixture: ComponentFixture<SGApproachDetailComponent>;
    const route = ({ data: of({ sGApproach: new SGApproach(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGApproachDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SGApproachDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGApproachDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sGApproach).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
