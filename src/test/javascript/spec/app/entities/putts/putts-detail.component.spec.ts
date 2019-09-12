/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { PuttsDetailComponent } from 'app/entities/putts/putts-detail.component';
import { Putts } from 'app/shared/model/putts.model';

describe('Component Tests', () => {
  describe('Putts Management Detail Component', () => {
    let comp: PuttsDetailComponent;
    let fixture: ComponentFixture<PuttsDetailComponent>;
    const route = ({ data: of({ putts: new Putts(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [PuttsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PuttsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuttsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.putts).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
