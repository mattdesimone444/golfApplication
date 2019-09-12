/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { DrivingDistanceComponent } from 'app/entities/driving-distance/driving-distance.component';
import { DrivingDistanceService } from 'app/entities/driving-distance/driving-distance.service';
import { DrivingDistance } from 'app/shared/model/driving-distance.model';

describe('Component Tests', () => {
  describe('DrivingDistance Management Component', () => {
    let comp: DrivingDistanceComponent;
    let fixture: ComponentFixture<DrivingDistanceComponent>;
    let service: DrivingDistanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DrivingDistanceComponent],
        providers: []
      })
        .overrideTemplate(DrivingDistanceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DrivingDistanceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DrivingDistanceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DrivingDistance(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.drivingDistances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
