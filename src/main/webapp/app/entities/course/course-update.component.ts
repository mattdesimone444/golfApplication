import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICourse, Course } from 'app/shared/model/course.model';
import { CourseService } from './course.service';

@Component({
  selector: 'jhi-course-update',
  templateUrl: './course-update.component.html'
})
export class CourseUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    par: [],
    yardage: [],
    city: [],
    state: [],
    courseType: [],
    greenType: [],
    hole1Par: [],
    hole1Yardage: [],
    hole1Handicap: [],
    hole2Par: [],
    hole2Yardage: [],
    hole2Handicap: [],
    hole3Par: [],
    hole3Yardage: [],
    hole3Handicap: [],
    hole4Par: [],
    hole4Yardage: [],
    hole4Handicap: [],
    hole5Par: [],
    hole5Yardage: [],
    hole5Handicap: [],
    hole6Par: [],
    hole6Yardage: [],
    hole6Handicap: [],
    hole7Par: [],
    hole7Yardage: [],
    hole7Handicap: [],
    hole8Par: [],
    hole8Yardage: [],
    hole8Handicap: [],
    hole9Par: [],
    hole9Yardage: [],
    hole9Handicap: [],
    hole10Par: [],
    hole10Yardage: [],
    hole10Handicap: [],
    hole11Par: [],
    hole11Yardage: [],
    hole11Handicap: [],
    hole12Par: [],
    hole12Yardage: [],
    hole12Handicap: [],
    hole13Par: [],
    hole13Yardage: [],
    hole13Handicap: [],
    hole14Par: [],
    hole14Yardage: [],
    hole14Handicap: [],
    hole15Par: [],
    hole15Yardage: [],
    hole15Handicap: [],
    hole16Par: [],
    hole16Yardage: [],
    hole16Handicap: [],
    hole17Par: [],
    hole17Yardage: [],
    hole17Handicap: [],
    hole18Par: [],
    hole18Yardage: [],
    hole18Handicap: []
  });

  constructor(protected courseService: CourseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ course }) => {
      this.updateForm(course);
    });
  }

  updateForm(course: ICourse) {
    this.editForm.patchValue({
      id: course.id,
      name: course.name,
      par: course.par,
      yardage: course.yardage,
      city: course.city,
      state: course.state,
      courseType: course.courseType,
      greenType: course.greenType,
      hole1Par: course.hole1Par,
      hole1Yardage: course.hole1Yardage,
      hole1Handicap: course.hole1Handicap,
      hole2Par: course.hole2Par,
      hole2Yardage: course.hole2Yardage,
      hole2Handicap: course.hole2Handicap,
      hole3Par: course.hole3Par,
      hole3Yardage: course.hole3Yardage,
      hole3Handicap: course.hole3Handicap,
      hole4Par: course.hole4Par,
      hole4Yardage: course.hole4Yardage,
      hole4Handicap: course.hole4Handicap,
      hole5Par: course.hole5Par,
      hole5Yardage: course.hole5Yardage,
      hole5Handicap: course.hole5Handicap,
      hole6Par: course.hole6Par,
      hole6Yardage: course.hole6Yardage,
      hole6Handicap: course.hole6Handicap,
      hole7Par: course.hole7Par,
      hole7Yardage: course.hole7Yardage,
      hole7Handicap: course.hole7Handicap,
      hole8Par: course.hole8Par,
      hole8Yardage: course.hole8Yardage,
      hole8Handicap: course.hole8Handicap,
      hole9Par: course.hole9Par,
      hole9Yardage: course.hole9Yardage,
      hole9Handicap: course.hole9Handicap,
      hole10Par: course.hole10Par,
      hole10Yardage: course.hole10Yardage,
      hole10Handicap: course.hole10Handicap,
      hole11Par: course.hole11Par,
      hole11Yardage: course.hole11Yardage,
      hole11Handicap: course.hole11Handicap,
      hole12Par: course.hole12Par,
      hole12Yardage: course.hole12Yardage,
      hole12Handicap: course.hole12Handicap,
      hole13Par: course.hole13Par,
      hole13Yardage: course.hole13Yardage,
      hole13Handicap: course.hole13Handicap,
      hole14Par: course.hole14Par,
      hole14Yardage: course.hole14Yardage,
      hole14Handicap: course.hole14Handicap,
      hole15Par: course.hole15Par,
      hole15Yardage: course.hole15Yardage,
      hole15Handicap: course.hole15Handicap,
      hole16Par: course.hole16Par,
      hole16Yardage: course.hole16Yardage,
      hole16Handicap: course.hole16Handicap,
      hole17Par: course.hole17Par,
      hole17Yardage: course.hole17Yardage,
      hole17Handicap: course.hole17Handicap,
      hole18Par: course.hole18Par,
      hole18Yardage: course.hole18Yardage,
      hole18Handicap: course.hole18Handicap
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const course = this.createFromForm();
    if (course.id !== undefined) {
      this.subscribeToSaveResponse(this.courseService.update(course));
    } else {
      this.subscribeToSaveResponse(this.courseService.create(course));
    }
  }

  private createFromForm(): ICourse {
    return {
      ...new Course(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      par: this.editForm.get(['par']).value,
      yardage: this.editForm.get(['yardage']).value,
      city: this.editForm.get(['city']).value,
      state: this.editForm.get(['state']).value,
      courseType: this.editForm.get(['courseType']).value,
      greenType: this.editForm.get(['greenType']).value,
      hole1Par: this.editForm.get(['hole1Par']).value,
      hole1Yardage: this.editForm.get(['hole1Yardage']).value,
      hole1Handicap: this.editForm.get(['hole1Handicap']).value,
      hole2Par: this.editForm.get(['hole2Par']).value,
      hole2Yardage: this.editForm.get(['hole2Yardage']).value,
      hole2Handicap: this.editForm.get(['hole2Handicap']).value,
      hole3Par: this.editForm.get(['hole3Par']).value,
      hole3Yardage: this.editForm.get(['hole3Yardage']).value,
      hole3Handicap: this.editForm.get(['hole3Handicap']).value,
      hole4Par: this.editForm.get(['hole4Par']).value,
      hole4Yardage: this.editForm.get(['hole4Yardage']).value,
      hole4Handicap: this.editForm.get(['hole4Handicap']).value,
      hole5Par: this.editForm.get(['hole5Par']).value,
      hole5Yardage: this.editForm.get(['hole5Yardage']).value,
      hole5Handicap: this.editForm.get(['hole5Handicap']).value,
      hole6Par: this.editForm.get(['hole6Par']).value,
      hole6Yardage: this.editForm.get(['hole6Yardage']).value,
      hole6Handicap: this.editForm.get(['hole6Handicap']).value,
      hole7Par: this.editForm.get(['hole7Par']).value,
      hole7Yardage: this.editForm.get(['hole7Yardage']).value,
      hole7Handicap: this.editForm.get(['hole7Handicap']).value,
      hole8Par: this.editForm.get(['hole8Par']).value,
      hole8Yardage: this.editForm.get(['hole8Yardage']).value,
      hole8Handicap: this.editForm.get(['hole8Handicap']).value,
      hole9Par: this.editForm.get(['hole9Par']).value,
      hole9Yardage: this.editForm.get(['hole9Yardage']).value,
      hole9Handicap: this.editForm.get(['hole9Handicap']).value,
      hole10Par: this.editForm.get(['hole10Par']).value,
      hole10Yardage: this.editForm.get(['hole10Yardage']).value,
      hole10Handicap: this.editForm.get(['hole10Handicap']).value,
      hole11Par: this.editForm.get(['hole11Par']).value,
      hole11Yardage: this.editForm.get(['hole11Yardage']).value,
      hole11Handicap: this.editForm.get(['hole11Handicap']).value,
      hole12Par: this.editForm.get(['hole12Par']).value,
      hole12Yardage: this.editForm.get(['hole12Yardage']).value,
      hole12Handicap: this.editForm.get(['hole12Handicap']).value,
      hole13Par: this.editForm.get(['hole13Par']).value,
      hole13Yardage: this.editForm.get(['hole13Yardage']).value,
      hole13Handicap: this.editForm.get(['hole13Handicap']).value,
      hole14Par: this.editForm.get(['hole14Par']).value,
      hole14Yardage: this.editForm.get(['hole14Yardage']).value,
      hole14Handicap: this.editForm.get(['hole14Handicap']).value,
      hole15Par: this.editForm.get(['hole15Par']).value,
      hole15Yardage: this.editForm.get(['hole15Yardage']).value,
      hole15Handicap: this.editForm.get(['hole15Handicap']).value,
      hole16Par: this.editForm.get(['hole16Par']).value,
      hole16Yardage: this.editForm.get(['hole16Yardage']).value,
      hole16Handicap: this.editForm.get(['hole16Handicap']).value,
      hole17Par: this.editForm.get(['hole17Par']).value,
      hole17Yardage: this.editForm.get(['hole17Yardage']).value,
      hole17Handicap: this.editForm.get(['hole17Handicap']).value,
      hole18Par: this.editForm.get(['hole18Par']).value,
      hole18Yardage: this.editForm.get(['hole18Yardage']).value,
      hole18Handicap: this.editForm.get(['hole18Handicap']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICourse>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
