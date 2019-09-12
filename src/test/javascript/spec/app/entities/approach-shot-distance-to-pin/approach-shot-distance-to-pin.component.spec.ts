/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { ApproachShotDistanceToPinComponent } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin.component';
import { ApproachShotDistanceToPinService } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin.service';
import { ApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';

describe('Component Tests', () => {
  describe('ApproachShotDistanceToPin Management Component', () => {
    let comp: ApproachShotDistanceToPinComponent;
    let fixture: ComponentFixture<ApproachShotDistanceToPinComponent>;
    let service: ApproachShotDistanceToPinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [ApproachShotDistanceToPinComponent],
        providers: []
      })
        .overrideTemplate(ApproachShotDistanceToPinComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApproachShotDistanceToPinComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApproachShotDistanceToPinService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApproachShotDistanceToPin(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.approachShotDistanceToPins[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
