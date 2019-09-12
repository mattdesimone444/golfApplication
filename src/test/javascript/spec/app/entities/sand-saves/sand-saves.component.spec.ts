/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GolfApplicationTestModule } from '../../../test.module';
import { SandSavesComponent } from 'app/entities/sand-saves/sand-saves.component';
import { SandSavesService } from 'app/entities/sand-saves/sand-saves.service';
import { SandSaves } from 'app/shared/model/sand-saves.model';

describe('Component Tests', () => {
  describe('SandSaves Management Component', () => {
    let comp: SandSavesComponent;
    let fixture: ComponentFixture<SandSavesComponent>;
    let service: SandSavesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SandSavesComponent],
        providers: []
      })
        .overrideTemplate(SandSavesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SandSavesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SandSavesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SandSaves(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sandSaves[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
