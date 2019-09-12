/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGArroundTheGreenComponent } from 'app/entities/sg-arround-the-green/sg-arround-the-green.component';
import { SGArroundTheGreenService } from 'app/entities/sg-arround-the-green/sg-arround-the-green.service';
import { SGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';

describe('Component Tests', () => {
  describe('SGArroundTheGreen Management Component', () => {
    let comp: SGArroundTheGreenComponent;
    let fixture: ComponentFixture<SGArroundTheGreenComponent>;
    let service: SGArroundTheGreenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGArroundTheGreenComponent],
        providers: []
      })
        .overrideTemplate(SGArroundTheGreenComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SGArroundTheGreenComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SGArroundTheGreenService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SGArroundTheGreen(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sGArroundTheGreens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
