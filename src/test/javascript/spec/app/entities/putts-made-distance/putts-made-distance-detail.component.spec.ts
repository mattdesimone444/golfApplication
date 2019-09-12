/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsMadeDistanceDetailComponent } from 'app/entities/putts-made-distance/putts-made-distance-detail.component';
import { PuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';

describe('Component Tests', () => {
  describe('PuttsMadeDistance Management Detail Component', () => {
    let comp: PuttsMadeDistanceDetailComponent;
    let fixture: ComponentFixture<PuttsMadeDistanceDetailComponent>;
    const route = ({ data: of({ puttsMadeDistance: new PuttsMadeDistance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsMadeDistanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PuttsMadeDistanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuttsMadeDistanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.puttsMadeDistance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
