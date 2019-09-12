/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGTeeToGreenDetailComponent } from 'app/entities/sg-tee-to-green/sg-tee-to-green-detail.component';
import { SGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';

describe('Component Tests', () => {
  describe('SGTeeToGreen Management Detail Component', () => {
    let comp: SGTeeToGreenDetailComponent;
    let fixture: ComponentFixture<SGTeeToGreenDetailComponent>;
    const route = ({ data: of({ sGTeeToGreen: new SGTeeToGreen(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGTeeToGreenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SGTeeToGreenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGTeeToGreenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sGTeeToGreen).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
