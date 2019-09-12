/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsMadeDistanceComponent } from 'app/entities/putts-made-distance/putts-made-distance.component';
import { PuttsMadeDistanceService } from 'app/entities/putts-made-distance/putts-made-distance.service';
import { PuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';

describe('Component Tests', () => {
  describe('PuttsMadeDistance Management Component', () => {
    let comp: PuttsMadeDistanceComponent;
    let fixture: ComponentFixture<PuttsMadeDistanceComponent>;
    let service: PuttsMadeDistanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsMadeDistanceComponent],
        providers: []
      })
        .overrideTemplate(PuttsMadeDistanceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuttsMadeDistanceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttsMadeDistanceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PuttsMadeDistance(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.puttsMadeDistances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
