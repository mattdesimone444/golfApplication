/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SandSavesDetailComponent } from 'app/entities/sand-saves/sand-saves-detail.component';
import { SandSaves } from 'app/shared/model/sand-saves.model';

describe('Component Tests', () => {
  describe('SandSaves Management Detail Component', () => {
    let comp: SandSavesDetailComponent;
    let fixture: ComponentFixture<SandSavesDetailComponent>;
    const route = ({ data: of({ sandSaves: new SandSaves(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SandSavesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SandSavesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SandSavesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sandSaves).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
