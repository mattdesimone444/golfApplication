import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDrivingDistance, DrivingDistance } from 'app/shared/model/driving-distance.model';
import { DrivingDistanceService } from './driving-distance.service';

@Component({
  selector: 'jhi-driving-distance-update',
  templateUrl: './driving-distance-update.component.html'
})
export class DrivingDistanceUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    playerId: [],
    tournamentId: [],
    courseId: [],
    hole1: [],
    hole2: [],
    hole3: [],
    hole4: [],
    hole5: [],
    hole6: [],
    hole7: [],
    hole8: [],
    hole9: [],
    hole10: [],
    hole11: [],
    hole12: [],
    hole13: [],
    hole14: [],
    hole15: [],
    hole16: [],
    hole17: [],
    hole18: []
  });

  constructor(
    protected drivingDistanceService: DrivingDistanceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ drivingDistance }) => {
      this.updateForm(drivingDistance);
    });
  }

  updateForm(drivingDistance: IDrivingDistance) {
    this.editForm.patchValue({
      id: drivingDistance.id,
      playerId: drivingDistance.playerId,
      tournamentId: drivingDistance.tournamentId,
      courseId: drivingDistance.courseId,
      hole1: drivingDistance.hole1,
      hole2: drivingDistance.hole2,
      hole3: drivingDistance.hole3,
      hole4: drivingDistance.hole4,
      hole5: drivingDistance.hole5,
      hole6: drivingDistance.hole6,
      hole7: drivingDistance.hole7,
      hole8: drivingDistance.hole8,
      hole9: drivingDistance.hole9,
      hole10: drivingDistance.hole10,
      hole11: drivingDistance.hole11,
      hole12: drivingDistance.hole12,
      hole13: drivingDistance.hole13,
      hole14: drivingDistance.hole14,
      hole15: drivingDistance.hole15,
      hole16: drivingDistance.hole16,
      hole17: drivingDistance.hole17,
      hole18: drivingDistance.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const drivingDistance = this.createFromForm();
    if (drivingDistance.id !== undefined) {
      this.subscribeToSaveResponse(this.drivingDistanceService.update(drivingDistance));
    } else {
      this.subscribeToSaveResponse(this.drivingDistanceService.create(drivingDistance));
    }
  }

  private createFromForm(): IDrivingDistance {
    return {
      ...new DrivingDistance(),
      id: this.editForm.get(['id']).value,
      playerId: this.editForm.get(['playerId']).value,
      tournamentId: this.editForm.get(['tournamentId']).value,
      courseId: this.editForm.get(['courseId']).value,
      hole1: this.editForm.get(['hole1']).value,
      hole2: this.editForm.get(['hole2']).value,
      hole3: this.editForm.get(['hole3']).value,
      hole4: this.editForm.get(['hole4']).value,
      hole5: this.editForm.get(['hole5']).value,
      hole6: this.editForm.get(['hole6']).value,
      hole7: this.editForm.get(['hole7']).value,
      hole8: this.editForm.get(['hole8']).value,
      hole9: this.editForm.get(['hole9']).value,
      hole10: this.editForm.get(['hole10']).value,
      hole11: this.editForm.get(['hole11']).value,
      hole12: this.editForm.get(['hole12']).value,
      hole13: this.editForm.get(['hole13']).value,
      hole14: this.editForm.get(['hole14']).value,
      hole15: this.editForm.get(['hole15']).value,
      hole16: this.editForm.get(['hole16']).value,
      hole17: this.editForm.get(['hole17']).value,
      hole18: this.editForm.get(['hole18']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDrivingDistance>>) {
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
