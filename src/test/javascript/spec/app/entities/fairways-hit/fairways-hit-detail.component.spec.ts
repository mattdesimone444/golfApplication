/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { FairwaysHitDetailComponent } from 'app/entities/fairways-hit/fairways-hit-detail.component';
import { FairwaysHit } from 'app/shared/model/fairways-hit.model';

describe('Component Tests', () => {
  describe('FairwaysHit Management Detail Component', () => {
    let comp: FairwaysHitDetailComponent;
    let fixture: ComponentFixture<FairwaysHitDetailComponent>;
    const route = ({ data: of({ fairwaysHit: new FairwaysHit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [FairwaysHitDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FairwaysHitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FairwaysHitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fairwaysHit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
