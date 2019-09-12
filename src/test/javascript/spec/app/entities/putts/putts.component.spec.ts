/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsComponent } from 'app/entities/putts/putts.component';
import { PuttsService } from 'app/entities/putts/putts.service';
import { Putts } from 'app/shared/model/putts.model';

describe('Component Tests', () => {
  describe('Putts Management Component', () => {
    let comp: PuttsComponent;
    let fixture: ComponentFixture<PuttsComponent>;
    let service: PuttsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsComponent],
        providers: []
      })
        .overrideTemplate(PuttsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuttsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuttsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Putts(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.putts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
