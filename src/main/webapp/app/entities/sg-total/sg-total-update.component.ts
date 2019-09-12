import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISGTotal, SGTotal } from 'app/shared/model/sg-total.model';
import { SGTotalService } from './sg-total.service';

@Component({
  selector: 'jhi-sg-total-update',
  templateUrl: './sg-total-update.component.html'
})
export class SGTotalUpdateComponent implements OnInit {
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

  constructor(protected sGTotalService: SGTotalService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sGTotal }) => {
      this.updateForm(sGTotal);
    });
  }

  updateForm(sGTotal: ISGTotal) {
    this.editForm.patchValue({
      id: sGTotal.id,
      playerId: sGTotal.playerId,
      tournamentId: sGTotal.tournamentId,
      courseId: sGTotal.courseId,
      hole1: sGTotal.hole1,
      hole2: sGTotal.hole2,
      hole3: sGTotal.hole3,
      hole4: sGTotal.hole4,
      hole5: sGTotal.hole5,
      hole6: sGTotal.hole6,
      hole7: sGTotal.hole7,
      hole8: sGTotal.hole8,
      hole9: sGTotal.hole9,
      hole10: sGTotal.hole10,
      hole11: sGTotal.hole11,
      hole12: sGTotal.hole12,
      hole13: sGTotal.hole13,
      hole14: sGTotal.hole14,
      hole15: sGTotal.hole15,
      hole16: sGTotal.hole16,
      hole17: sGTotal.hole17,
      hole18: sGTotal.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sGTotal = this.createFromForm();
    if (sGTotal.id !== undefined) {
      this.subscribeToSaveResponse(this.sGTotalService.update(sGTotal));
    } else {
      this.subscribeToSaveResponse(this.sGTotalService.create(sGTotal));
    }
  }

  private createFromForm(): ISGTotal {
    return {
      ...new SGTotal(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISGTotal>>) {
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
