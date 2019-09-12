import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPuttsMadeDistance, PuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';
import { PuttsMadeDistanceService } from './putts-made-distance.service';

@Component({
  selector: 'jhi-putts-made-distance-update',
  templateUrl: './putts-made-distance-update.component.html'
})
export class PuttsMadeDistanceUpdateComponent implements OnInit {
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
    protected puttsMadeDistanceService: PuttsMadeDistanceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ puttsMadeDistance }) => {
      this.updateForm(puttsMadeDistance);
    });
  }

  updateForm(puttsMadeDistance: IPuttsMadeDistance) {
    this.editForm.patchValue({
      id: puttsMadeDistance.id,
      playerId: puttsMadeDistance.playerId,
      tournamentId: puttsMadeDistance.tournamentId,
      courseId: puttsMadeDistance.courseId,
      hole1: puttsMadeDistance.hole1,
      hole2: puttsMadeDistance.hole2,
      hole3: puttsMadeDistance.hole3,
      hole4: puttsMadeDistance.hole4,
      hole5: puttsMadeDistance.hole5,
      hole6: puttsMadeDistance.hole6,
      hole7: puttsMadeDistance.hole7,
      hole8: puttsMadeDistance.hole8,
      hole9: puttsMadeDistance.hole9,
      hole10: puttsMadeDistance.hole10,
      hole11: puttsMadeDistance.hole11,
      hole12: puttsMadeDistance.hole12,
      hole13: puttsMadeDistance.hole13,
      hole14: puttsMadeDistance.hole14,
      hole15: puttsMadeDistance.hole15,
      hole16: puttsMadeDistance.hole16,
      hole17: puttsMadeDistance.hole17,
      hole18: puttsMadeDistance.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const puttsMadeDistance = this.createFromForm();
    if (puttsMadeDistance.id !== undefined) {
      this.subscribeToSaveResponse(this.puttsMadeDistanceService.update(puttsMadeDistance));
    } else {
      this.subscribeToSaveResponse(this.puttsMadeDistanceService.create(puttsMadeDistance));
    }
  }

  private createFromForm(): IPuttsMadeDistance {
    return {
      ...new PuttsMadeDistance(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPuttsMadeDistance>>) {
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
