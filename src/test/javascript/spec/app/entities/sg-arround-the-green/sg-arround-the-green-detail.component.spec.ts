/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGArroundTheGreenDetailComponent } from 'app/entities/sg-arround-the-green/sg-arround-the-green-detail.component';
import { SGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';

describe('Component Tests', () => {
  describe('SGArroundTheGreen Management Detail Component', () => {
    let comp: SGArroundTheGreenDetailComponent;
    let fixture: ComponentFixture<SGArroundTheGreenDetailComponent>;
    const route = ({ data: of({ sGArroundTheGreen: new SGArroundTheGreen(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGArroundTheGreenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SGArroundTheGreenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGArroundTheGreenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sGArroundTheGreen).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
