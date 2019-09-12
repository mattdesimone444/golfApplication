/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CourseService } from 'app/entities/course/course.service';
import { ICourse, Course } from 'app/shared/model/course.model';

describe('Service Tests', () => {
  describe('Course Service', () => {
    let injector: TestBed;
    let service: CourseService;
    let httpMock: HttpTestingController;
    let elemDefault: ICourse;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CourseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Course(
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
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

      it('should create a Course', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Course(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Course', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            par: 1,
            yardage: 1,
            city: 'BBBBBB',
            state: 'BBBBBB',
            courseType: 'BBBBBB',
            greenType: 'BBBBBB',
            hole1Par: 1,
            hole1Yardage: 1,
            hole1Handicap: 1,
            hole2Par: 1,
            hole2Yardage: 1,
            hole2Handicap: 1,
            hole3Par: 1,
            hole3Yardage: 1,
            hole3Handicap: 1,
            hole4Par: 1,
            hole4Yardage: 1,
            hole4Handicap: 1,
            hole5Par: 1,
            hole5Yardage: 1,
            hole5Handicap: 1,
            hole6Par: 1,
            hole6Yardage: 1,
            hole6Handicap: 1,
            hole7Par: 1,
            hole7Yardage: 1,
            hole7Handicap: 1,
            hole8Par: 1,
            hole8Yardage: 1,
            hole8Handicap: 1,
            hole9Par: 1,
            hole9Yardage: 1,
            hole9Handicap: 1,
            hole10Par: 1,
            hole10Yardage: 1,
            hole10Handicap: 1,
            hole11Par: 1,
            hole11Yardage: 1,
            hole11Handicap: 1,
            hole12Par: 1,
            hole12Yardage: 1,
            hole12Handicap: 1,
            hole13Par: 1,
            hole13Yardage: 1,
            hole13Handicap: 1,
            hole14Par: 1,
            hole14Yardage: 1,
            hole14Handicap: 1,
            hole15Par: 1,
            hole15Yardage: 1,
            hole15Handicap: 1,
            hole16Par: 1,
            hole16Yardage: 1,
            hole16Handicap: 1,
            hole17Par: 1,
            hole17Yardage: 1,
            hole17Handicap: 1,
            hole18Par: 1,
            hole18Yardage: 1,
            hole18Handicap: 1
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

      it('should return a list of Course', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            par: 1,
            yardage: 1,
            city: 'BBBBBB',
            state: 'BBBBBB',
            courseType: 'BBBBBB',
            greenType: 'BBBBBB',
            hole1Par: 1,
            hole1Yardage: 1,
            hole1Handicap: 1,
            hole2Par: 1,
            hole2Yardage: 1,
            hole2Handicap: 1,
            hole3Par: 1,
            hole3Yardage: 1,
            hole3Handicap: 1,
            hole4Par: 1,
            hole4Yardage: 1,
            hole4Handicap: 1,
            hole5Par: 1,
            hole5Yardage: 1,
            hole5Handicap: 1,
            hole6Par: 1,
            hole6Yardage: 1,
            hole6Handicap: 1,
            hole7Par: 1,
            hole7Yardage: 1,
            hole7Handicap: 1,
            hole8Par: 1,
            hole8Yardage: 1,
            hole8Handicap: 1,
            hole9Par: 1,
            hole9Yardage: 1,
            hole9Handicap: 1,
            hole10Par: 1,
            hole10Yardage: 1,
            hole10Handicap: 1,
            hole11Par: 1,
            hole11Yardage: 1,
            hole11Handicap: 1,
            hole12Par: 1,
            hole12Yardage: 1,
            hole12Handicap: 1,
            hole13Par: 1,
            hole13Yardage: 1,
            hole13Handicap: 1,
            hole14Par: 1,
            hole14Yardage: 1,
            hole14Handicap: 1,
            hole15Par: 1,
            hole15Yardage: 1,
            hole15Handicap: 1,
            hole16Par: 1,
            hole16Yardage: 1,
            hole16Handicap: 1,
            hole17Par: 1,
            hole17Yardage: 1,
            hole17Handicap: 1,
            hole18Par: 1,
            hole18Yardage: 1,
            hole18Handicap: 1
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

      it('should delete a Course', async () => {
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
