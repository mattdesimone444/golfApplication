/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { FairwaysHitComponent } from 'app/entities/fairways-hit/fairways-hit.component';
import { FairwaysHitService } from 'app/entities/fairways-hit/fairways-hit.service';
import { FairwaysHit } from 'app/shared/model/fairways-hit.model';

describe('Component Tests', () => {
  describe('FairwaysHit Management Component', () => {
    let comp: FairwaysHitComponent;
    let fixture: ComponentFixture<FairwaysHitComponent>;
    let service: FairwaysHitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [FairwaysHitComponent],
        providers: []
      })
        .overrideTemplate(FairwaysHitComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FairwaysHitComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FairwaysHitService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FairwaysHit(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fairwaysHits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
