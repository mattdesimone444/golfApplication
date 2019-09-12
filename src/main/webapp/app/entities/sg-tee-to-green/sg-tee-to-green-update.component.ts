import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISGTeeToGreen, SGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';
import { SGTeeToGreenService } from './sg-tee-to-green.service';

@Component({
  selector: 'jhi-sg-tee-to-green-update',
  templateUrl: './sg-tee-to-green-update.component.html'
})
export class SGTeeToGreenUpdateComponent implements OnInit {
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

  constructor(protected sGTeeToGreenService: SGTeeToGreenService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sGTeeToGreen }) => {
      this.updateForm(sGTeeToGreen);
    });
  }

  updateForm(sGTeeToGreen: ISGTeeToGreen) {
    this.editForm.patchValue({
      id: sGTeeToGreen.id,
      playerId: sGTeeToGreen.playerId,
      tournamentId: sGTeeToGreen.tournamentId,
      courseId: sGTeeToGreen.courseId,
      hole1: sGTeeToGreen.hole1,
      hole2: sGTeeToGreen.hole2,
      hole3: sGTeeToGreen.hole3,
      hole4: sGTeeToGreen.hole4,
      hole5: sGTeeToGreen.hole5,
      hole6: sGTeeToGreen.hole6,
      hole7: sGTeeToGreen.hole7,
      hole8: sGTeeToGreen.hole8,
      hole9: sGTeeToGreen.hole9,
      hole10: sGTeeToGreen.hole10,
      hole11: sGTeeToGreen.hole11,
      hole12: sGTeeToGreen.hole12,
      hole13: sGTeeToGreen.hole13,
      hole14: sGTeeToGreen.hole14,
      hole15: sGTeeToGreen.hole15,
      hole16: sGTeeToGreen.hole16,
      hole17: sGTeeToGreen.hole17,
      hole18: sGTeeToGreen.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sGTeeToGreen = this.createFromForm();
    if (sGTeeToGreen.id !== undefined) {
      this.subscribeToSaveResponse(this.sGTeeToGreenService.update(sGTeeToGreen));
    } else {
      this.subscribeToSaveResponse(this.sGTeeToGreenService.create(sGTeeToGreen));
    }
  }

  private createFromForm(): ISGTeeToGreen {
    return {
      ...new SGTeeToGreen(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISGTeeToGreen>>) {
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
