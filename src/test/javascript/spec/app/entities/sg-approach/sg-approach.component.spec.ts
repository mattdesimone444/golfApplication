/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGApproachComponent } from 'app/entities/sg-approach/sg-approach.component';
import { SGApproachService } from 'app/entities/sg-approach/sg-approach.service';
import { SGApproach } from 'app/shared/model/sg-approach.model';

describe('Component Tests', () => {
  describe('SGApproach Management Component', () => {
    let comp: SGApproachComponent;
    let fixture: ComponentFixture<SGApproachComponent>;
    let service: SGApproachService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGApproachComponent],
        providers: []
      })
        .overrideTemplate(SGApproachComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGApproachComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGApproachService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SGApproach(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sGApproaches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
