/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { ScoresDetailComponent } from 'app/entities/scores/scores-detail.component';
import { Scores } from 'app/shared/model/scores.model';

describe('Component Tests', () => {
  describe('Scores Management Detail Component', () => {
    let comp: ScoresDetailComponent;
    let fixture: ComponentFixture<ScoresDetailComponent>;
    const route = ({ data: of({ scores: new Scores(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [ScoresDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ScoresDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScoresDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.scores).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
