import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IFairwaysHit, FairwaysHit } from 'app/shared/model/fairways-hit.model';
import { FairwaysHitService } from './fairways-hit.service';

@Component({
  selector: 'jhi-fairways-hit-update',
  templateUrl: './fairways-hit-update.component.html'
})
export class FairwaysHitUpdateComponent implements OnInit {
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

  constructor(protected fairwaysHitService: FairwaysHitService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fairwaysHit }) => {
      this.updateForm(fairwaysHit);
    });
  }

  updateForm(fairwaysHit: IFairwaysHit) {
    this.editForm.patchValue({
      id: fairwaysHit.id,
      playerId: fairwaysHit.playerId,
      tournamentId: fairwaysHit.tournamentId,
      courseId: fairwaysHit.courseId,
      hole1: fairwaysHit.hole1,
      hole2: fairwaysHit.hole2,
      hole3: fairwaysHit.hole3,
      hole4: fairwaysHit.hole4,
      hole5: fairwaysHit.hole5,
      hole6: fairwaysHit.hole6,
      hole7: fairwaysHit.hole7,
      hole8: fairwaysHit.hole8,
      hole9: fairwaysHit.hole9,
      hole10: fairwaysHit.hole10,
      hole11: fairwaysHit.hole11,
      hole12: fairwaysHit.hole12,
      hole13: fairwaysHit.hole13,
      hole14: fairwaysHit.hole14,
      hole15: fairwaysHit.hole15,
      hole16: fairwaysHit.hole16,
      hole17: fairwaysHit.hole17,
      hole18: fairwaysHit.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fairwaysHit = this.createFromForm();
    if (fairwaysHit.id !== undefined) {
      this.subscribeToSaveResponse(this.fairwaysHitService.update(fairwaysHit));
    } else {
      this.subscribeToSaveResponse(this.fairwaysHitService.create(fairwaysHit));
    }
  }

  private createFromForm(): IFairwaysHit {
    return {
      ...new FairwaysHit(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFairwaysHit>>) {
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
