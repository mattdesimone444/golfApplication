import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDistanceOfApproach, DistanceOfApproach } from 'app/shared/model/distance-of-approach.model';
import { DistanceOfApproachService } from './distance-of-approach.service';

@Component({
  selector: 'jhi-distance-of-approach-update',
  templateUrl: './distance-of-approach-update.component.html'
})
export class DistanceOfApproachUpdateComponent implements OnInit {
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
    protected distanceOfApproachService: DistanceOfApproachService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ distanceOfApproach }) => {
      this.updateForm(distanceOfApproach);
    });
  }

  updateForm(distanceOfApproach: IDistanceOfApproach) {
    this.editForm.patchValue({
      id: distanceOfApproach.id,
      playerId: distanceOfApproach.playerId,
      tournamentId: distanceOfApproach.tournamentId,
      courseId: distanceOfApproach.courseId,
      hole1: distanceOfApproach.hole1,
      hole2: distanceOfApproach.hole2,
      hole3: distanceOfApproach.hole3,
      hole4: distanceOfApproach.hole4,
      hole5: distanceOfApproach.hole5,
      hole6: distanceOfApproach.hole6,
      hole7: distanceOfApproach.hole7,
      hole8: distanceOfApproach.hole8,
      hole9: distanceOfApproach.hole9,
      hole10: distanceOfApproach.hole10,
      hole11: distanceOfApproach.hole11,
      hole12: distanceOfApproach.hole12,
      hole13: distanceOfApproach.hole13,
      hole14: distanceOfApproach.hole14,
      hole15: distanceOfApproach.hole15,
      hole16: distanceOfApproach.hole16,
      hole17: distanceOfApproach.hole17,
      hole18: distanceOfApproach.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const distanceOfApproach = this.createFromForm();
    if (distanceOfApproach.id !== undefined) {
      this.subscribeToSaveResponse(this.distanceOfApproachService.update(distanceOfApproach));
    } else {
      this.subscribeToSaveResponse(this.distanceOfApproachService.create(distanceOfApproach));
    }
  }

  private createFromForm(): IDistanceOfApproach {
    return {
      ...new DistanceOfApproach(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDistanceOfApproach>>) {
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
