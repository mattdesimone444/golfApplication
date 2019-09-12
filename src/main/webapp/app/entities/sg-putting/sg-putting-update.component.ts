import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISGPutting, SGPutting } from 'app/shared/model/sg-putting.model';
import { SGPuttingService } from './sg-putting.service';

@Component({
  selector: 'jhi-sg-putting-update',
  templateUrl: './sg-putting-update.component.html'
})
export class SGPuttingUpdateComponent implements OnInit {
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

  constructor(protected sGPuttingService: SGPuttingService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sGPutting }) => {
      this.updateForm(sGPutting);
    });
  }

  updateForm(sGPutting: ISGPutting) {
    this.editForm.patchValue({
      id: sGPutting.id,
      playerId: sGPutting.playerId,
      tournamentId: sGPutting.tournamentId,
      courseId: sGPutting.courseId,
      hole1: sGPutting.hole1,
      hole2: sGPutting.hole2,
      hole3: sGPutting.hole3,
      hole4: sGPutting.hole4,
      hole5: sGPutting.hole5,
      hole6: sGPutting.hole6,
      hole7: sGPutting.hole7,
      hole8: sGPutting.hole8,
      hole9: sGPutting.hole9,
      hole10: sGPutting.hole10,
      hole11: sGPutting.hole11,
      hole12: sGPutting.hole12,
      hole13: sGPutting.hole13,
      hole14: sGPutting.hole14,
      hole15: sGPutting.hole15,
      hole16: sGPutting.hole16,
      hole17: sGPutting.hole17,
      hole18: sGPutting.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sGPutting = this.createFromForm();
    if (sGPutting.id !== undefined) {
      this.subscribeToSaveResponse(this.sGPuttingService.update(sGPutting));
    } else {
      this.subscribeToSaveResponse(this.sGPuttingService.create(sGPutting));
    }
  }

  private createFromForm(): ISGPutting {
    return {
      ...new SGPutting(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISGPutting>>) {
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
