/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { ApproachShotDistanceToPinDetailComponent } from 'app/entities/approach-shot-distance-to-pin/approach-shot-distance-to-pin-detail.component';
import { ApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';

describe('Component Tests', () => {
  describe('ApproachShotDistanceToPin Management Detail Component', () => {
    let comp: ApproachShotDistanceToPinDetailComponent;
    let fixture: ComponentFixture<ApproachShotDistanceToPinDetailComponent>;
    const route = ({ data: of({ approachShotDistanceToPin: new ApproachShotDistanceToPin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [ApproachShotDistanceToPinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApproachShotDistanceToPinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApproachShotDistanceToPinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.approachShotDistanceToPin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
