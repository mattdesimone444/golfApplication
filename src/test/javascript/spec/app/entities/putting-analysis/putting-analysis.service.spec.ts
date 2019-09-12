/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { PuttingAnalysisService } from 'app/entities/putting-analysis/putting-analysis.service';
import { IPuttingAnalysis, PuttingAnalysis } from 'app/shared/model/putting-analysis.model';

describe('Service Tests', () => {
  describe('PuttingAnalysis Service', () => {
    let injector: TestBed;
    let service: PuttingAnalysisService;
    let httpMock: HttpTestingController;
    let elemDefault: IPuttingAnalysis;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PuttingAnalysisService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PuttingAnalysis(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
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

      it('should create a PuttingAnalysis', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new PuttingAnalysis(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a PuttingAnalysis', async () => {
        const returnedFromService = Object.assign(
          {
            puttinAnalysisId: 1,
            longest: 'BBBBBB',
            total: 'BBBBBB',
            lessThree: 'BBBBBB',
            lessTen: 'BBBBBB',
            threeToFive: 'BBBBBB',
            fiveToSeven: 'BBBBBB',
            sevenToTen: 'BBBBBB',
            fourToEight: 'BBBBBB',
            tenToFifteen: 'BBBBBB',
            fifteenToTwenty: 'BBBBBB',
            twentyToTwentyFive: 'BBBBBB',
            lessTwentyFive: 'BBBBBB'
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

      it('should return a list of PuttingAnalysis', async () => {
        const returnedFromService = Object.assign(
          {
            puttinAnalysisId: 1,
            longest: 'BBBBBB',
            total: 'BBBBBB',
            lessThree: 'BBBBBB',
            lessTen: 'BBBBBB',
            threeToFive: 'BBBBBB',
            fiveToSeven: 'BBBBBB',
            sevenToTen: 'BBBBBB',
            fourToEight: 'BBBBBB',
            tenToFifteen: 'BBBBBB',
            fifteenToTwenty: 'BBBBBB',
            twentyToTwentyFive: 'BBBBBB',
            lessTwentyFive: 'BBBBBB'
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

      it('should delete a PuttingAnalysis', async () => {
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
