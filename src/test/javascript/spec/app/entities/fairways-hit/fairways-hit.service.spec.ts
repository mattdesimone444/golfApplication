/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { FairwaysHitService } from 'app/entities/fairways-hit/fairways-hit.service';
import { IFairwaysHit, FairwaysHit } from 'app/shared/model/fairways-hit.model';

describe('Service Tests', () => {
  describe('FairwaysHit Service', () => {
    let injector: TestBed;
    let service: FairwaysHitService;
    let httpMock: HttpTestingController;
    let elemDefault: IFairwaysHit;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(FairwaysHitService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new FairwaysHit(
        0,
        0,
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

      it('should create a FairwaysHit', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new FairwaysHit(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a FairwaysHit', async () => {
        const returnedFromService = Object.assign(
          {
            playerId: 1,
            tournamentId: 1,
            courseId: 1,
            hole1: 'BBBBBB',
            hole2: 'BBBBBB',
            hole3: 'BBBBBB',
            hole4: 'BBBBBB',
            hole5: 'BBBBBB',
            hole6: 'BBBBBB',
            hole7: 'BBBBBB',
            hole8: 'BBBBBB',
            hole9: 'BBBBBB',
            hole10: 'BBBBBB',
            hole11: 'BBBBBB',
            hole12: 'BBBBBB',
            hole13: 'BBBBBB',
            hole14: 'BBBBBB',
            hole15: 'BBBBBB',
            hole16: 'BBBBBB',
            hole17: 'BBBBBB',
            hole18: 'BBBBBB'
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

      it('should return a list of FairwaysHit', async () => {
        const returnedFromService = Object.assign(
          {
            playerId: 1,
            tournamentId: 1,
            courseId: 1,
            hole1: 'BBBBBB',
            hole2: 'BBBBBB',
            hole3: 'BBBBBB',
            hole4: 'BBBBBB',
            hole5: 'BBBBBB',
            hole6: 'BBBBBB',
            hole7: 'BBBBBB',
            hole8: 'BBBBBB',
            hole9: 'BBBBBB',
            hole10: 'BBBBBB',
            hole11: 'BBBBBB',
            hole12: 'BBBBBB',
            hole13: 'BBBBBB',
            hole14: 'BBBBBB',
            hole15: 'BBBBBB',
            hole16: 'BBBBBB',
            hole17: 'BBBBBB',
            hole18: 'BBBBBB'
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

      it('should delete a FairwaysHit', async () => {
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
