/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { SGOffTheTeeService } from 'app/entities/sg-off-the-tee/sg-off-the-tee.service';
import { ISGOffTheTee, SGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

describe('Service Tests', () => {
  describe('SGOffTheTee Service', () => {
    let injector: TestBed;
    let service: SGOffTheTeeService;
    let httpMock: HttpTestingController;
    let elemDefault: ISGOffTheTee;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SGOffTheTeeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SGOffTheTee(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a SGOffTheTee', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new SGOffTheTee(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a SGOffTheTee', async () => {
        const returnedFromService = Object.assign(
          {
            playerId: 1,
            tournamentId: 1,
            courseId: 1,
            hole1: 1,
            hole2: 1,
            hole3: 1,
            hole4: 1,
            hole5: 1,
            hole6: 1,
            hole7: 1,
            hole8: 1,
            hole9: 1,
            hole10: 1,
            hole11: 1,
            hole12: 1,
            hole13: 1,
            hole14: 1,
            hole15: 1,
            hole16: 1,
            hole17: 1,
            hole18: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of SGOffTheTee', async () => {
        const returnedFromService = Object.assign(
          {
            playerId: 1,
            tournamentId: 1,
            courseId: 1,
            hole1: 1,
            hole2: 1,
            hole3: 1,
            hole4: 1,
            hole5: 1,
            hole6: 1,
            hole7: 1,
            hole8: 1,
            hole9: 1,
            hole10: 1,
            hole11: 1,
            hole12: 1,
            hole13: 1,
            hole14: 1,
            hole15: 1,
            hole16: 1,
            hole17: 1,
            hole18: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SGOffTheTee', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
