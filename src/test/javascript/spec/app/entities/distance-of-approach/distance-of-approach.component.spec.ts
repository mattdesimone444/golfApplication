/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { DistanceOfApproachComponent } from 'app/entities/distance-of-approach/distance-of-approach.component';
import { DistanceOfApproachService } from 'app/entities/distance-of-approach/distance-of-approach.service';
import { DistanceOfApproach } from 'app/shared/model/distance-of-approach.model';

describe('Component Tests', () => {
  describe('DistanceOfApproach Management Component', () => {
    let comp: DistanceOfApproachComponent;
    let fixture: ComponentFixture<DistanceOfApproachComponent>;
    let service: DistanceOfApproachService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [DistanceOfApproachComponent],
        providers: []
      })
        .overrideTemplate(DistanceOfApproachComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistanceOfApproachComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceOfApproachService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DistanceOfApproach(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.distanceOfApproaches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
