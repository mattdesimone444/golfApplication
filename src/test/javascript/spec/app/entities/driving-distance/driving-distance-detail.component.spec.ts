/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { DrivingDistanceDetailComponent } from 'app/entities/driving-distance/driving-distance-detail.component';
import { DrivingDistance } from 'app/shared/model/driving-distance.model';

describe('Component Tests', () => {
  describe('DrivingDistance Management Detail Component', () => {
    let comp: DrivingDistanceDetailComponent;
    let fixture: ComponentFixture<DrivingDistanceDetailComponent>;
    const route = ({ data: of({ drivingDistance: new DrivingDistance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DrivingDistanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DrivingDistanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DrivingDistanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.drivingDistance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
