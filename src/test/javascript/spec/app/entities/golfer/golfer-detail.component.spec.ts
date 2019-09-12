/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { GolferDetailComponent } from 'app/entities/golfer/golfer-detail.component';
import { Golfer } from 'app/shared/model/golfer.model';

describe('Component Tests', () => {
  describe('Golfer Management Detail Component', () => {
    let comp: GolferDetailComponent;
    let fixture: ComponentFixture<GolferDetailComponent>;
    const route = ({ data: of({ golfer: new Golfer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [GolferDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GolferDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GolferDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.golfer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
