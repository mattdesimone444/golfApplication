/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { DistanceOfApproachDetailComponent } from 'app/entities/distance-of-approach/distance-of-approach-detail.component';
import { DistanceOfApproach } from 'app/shared/model/distance-of-approach.model';

describe('Component Tests', () => {
  describe('DistanceOfApproach Management Detail Component', () => {
    let comp: DistanceOfApproachDetailComponent;
    let fixture: ComponentFixture<DistanceOfApproachDetailComponent>;
    const route = ({ data: of({ distanceOfApproach: new DistanceOfApproach(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DistanceOfApproachDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DistanceOfApproachDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DistanceOfApproachDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.distanceOfApproach).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
