import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IScores, Scores } from 'app/shared/model/scores.model';
import { ScoresService } from './scores.service';

@Component({
  selector: 'jhi-scores-update',
  templateUrl: './scores-update.component.html'
})
export class ScoresUpdateComponent implements OnInit {
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

  constructor(protected scoresService: ScoresService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ scores }) => {
      this.updateForm(scores);
    });
  }

  updateForm(scores: IScores) {
    this.editForm.patchValue({
      id: scores.id,
      playerId: scores.playerId,
      tournamentId: scores.tournamentId,
      courseId: scores.courseId,
      hole1: scores.hole1,
      hole2: scores.hole2,
      hole3: scores.hole3,
      hole4: scores.hole4,
      hole5: scores.hole5,
      hole6: scores.hole6,
      hole7: scores.hole7,
      hole8: scores.hole8,
      hole9: scores.hole9,
      hole10: scores.hole10,
      hole11: scores.hole11,
      hole12: scores.hole12,
      hole13: scores.hole13,
      hole14: scores.hole14,
      hole15: scores.hole15,
      hole16: scores.hole16,
      hole17: scores.hole17,
      hole18: scores.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const scores = this.createFromForm();
    if (scores.id !== undefined) {
      this.subscribeToSaveResponse(this.scoresService.update(scores));
    } else {
      this.subscribeToSaveResponse(this.scoresService.create(scores));
    }
  }

  private createFromForm(): IScores {
    return {
      ...new Scores(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScores>>) {
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
