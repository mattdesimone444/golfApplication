/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GolfApplicationTestModule } from '../../../test.module';
import { SGOffTheTeeDetailComponent } from 'app/entities/sg-off-the-tee/sg-off-the-tee-detail.component';
import { SGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

describe('Component Tests', () => {
  describe('SGOffTheTee Management Detail Component', () => {
    let comp: SGOffTheTeeDetailComponent;
    let fixture: ComponentFixture<SGOffTheTeeDetailComponent>;
    const route = ({ data: of({ sGOffTheTee: new SGOffTheTee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GolfApplicationTestModule],
        declarations: [SGOffTheTeeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SGOffTheTeeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SGOffTheTeeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sGOffTheTee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
