import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IApproachShotDistanceToPin, ApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';
import { ApproachShotDistanceToPinService } from './approach-shot-distance-to-pin.service';

@Component({
  selector: 'jhi-approach-shot-distance-to-pin-update',
  templateUrl: './approach-shot-distance-to-pin-update.component.html'
})
export class ApproachShotDistanceToPinUpdateComponent implements OnInit {
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
    protected approachShotDistanceToPinService: ApproachShotDistanceToPinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ approachShotDistanceToPin }) => {
      this.updateForm(approachShotDistanceToPin);
    });
  }

  updateForm(approachShotDistanceToPin: IApproachShotDistanceToPin) {
    this.editForm.patchValue({
      id: approachShotDistanceToPin.id,
      playerId: approachShotDistanceToPin.playerId,
      tournamentId: approachShotDistanceToPin.tournamentId,
      courseId: approachShotDistanceToPin.courseId,
      hole1: approachShotDistanceToPin.hole1,
      hole2: approachShotDistanceToPin.hole2,
      hole3: approachShotDistanceToPin.hole3,
      hole4: approachShotDistanceToPin.hole4,
      hole5: approachShotDistanceToPin.hole5,
      hole6: approachShotDistanceToPin.hole6,
      hole7: approachShotDistanceToPin.hole7,
      hole8: approachShotDistanceToPin.hole8,
      hole9: approachShotDistanceToPin.hole9,
      hole10: approachShotDistanceToPin.hole10,
      hole11: approachShotDistanceToPin.hole11,
      hole12: approachShotDistanceToPin.hole12,
      hole13: approachShotDistanceToPin.hole13,
      hole14: approachShotDistanceToPin.hole14,
      hole15: approachShotDistanceToPin.hole15,
      hole16: approachShotDistanceToPin.hole16,
      hole17: approachShotDistanceToPin.hole17,
      hole18: approachShotDistanceToPin.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const approachShotDistanceToPin = this.createFromForm();
    if (approachShotDistanceToPin.id !== undefined) {
      this.subscribeToSaveResponse(this.approachShotDistanceToPinService.update(approachShotDistanceToPin));
    } else {
      this.subscribeToSaveResponse(this.approachShotDistanceToPinService.create(approachShotDistanceToPin));
    }
  }

  private createFromForm(): IApproachShotDistanceToPin {
    return {
      ...new ApproachShotDistanceToPin(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApproachShotDistanceToPin>>) {
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
