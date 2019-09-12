/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { GolferComponent } from 'app/entities/golfer/golfer.component';
import { GolferService } from 'app/entities/golfer/golfer.service';
import { Golfer } from 'app/shared/model/golfer.model';

describe('Component Tests', () => {
  describe('Golfer Management Component', () => {
    let comp: GolferComponent;
    let fixture: ComponentFixture<GolferComponent>;
    let service: GolferService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GolferComponent],
        providers: []
      })
        .overrideTemplate(GolferComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GolferComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GolferService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Golfer(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.golfers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
