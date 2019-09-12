import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISGArroundTheGreen, SGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';
import { SGArroundTheGreenService } from './sg-arround-the-green.service';

@Component({
  selector: 'jhi-sg-arround-the-green-update',
  templateUrl: './sg-arround-the-green-update.component.html'
})
export class SGArroundTheGreenUpdateComponent implements OnInit {
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
    protected sGArroundTheGreenService: SGArroundTheGreenService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sGArroundTheGreen }) => {
      this.updateForm(sGArroundTheGreen);
    });
  }

  updateForm(sGArroundTheGreen: ISGArroundTheGreen) {
    this.editForm.patchValue({
      id: sGArroundTheGreen.id,
      playerId: sGArroundTheGreen.playerId,
      tournamentId: sGArroundTheGreen.tournamentId,
      courseId: sGArroundTheGreen.courseId,
      hole1: sGArroundTheGreen.hole1,
      hole2: sGArroundTheGreen.hole2,
      hole3: sGArroundTheGreen.hole3,
      hole4: sGArroundTheGreen.hole4,
      hole5: sGArroundTheGreen.hole5,
      hole6: sGArroundTheGreen.hole6,
      hole7: sGArroundTheGreen.hole7,
      hole8: sGArroundTheGreen.hole8,
      hole9: sGArroundTheGreen.hole9,
      hole10: sGArroundTheGreen.hole10,
      hole11: sGArroundTheGreen.hole11,
      hole12: sGArroundTheGreen.hole12,
      hole13: sGArroundTheGreen.hole13,
      hole14: sGArroundTheGreen.hole14,
      hole15: sGArroundTheGreen.hole15,
      hole16: sGArroundTheGreen.hole16,
      hole17: sGArroundTheGreen.hole17,
      hole18: sGArroundTheGreen.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sGArroundTheGreen = this.createFromForm();
    if (sGArroundTheGreen.id !== undefined) {
      this.subscribeToSaveResponse(this.sGArroundTheGreenService.update(sGArroundTheGreen));
    } else {
      this.subscribeToSaveResponse(this.sGArroundTheGreenService.create(sGArroundTheGreen));
    }
  }

  private createFromForm(): ISGArroundTheGreen {
    return {
      ...new SGArroundTheGreen(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISGArroundTheGreen>>) {
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
