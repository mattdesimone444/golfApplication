import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPutts, Putts } from 'app/shared/model/putts.model';
import { PuttsService } from './putts.service';

@Component({
  selector: 'jhi-putts-update',
  templateUrl: './putts-update.component.html'
})
export class PuttsUpdateComponent implements OnInit {
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

  constructor(protected puttsService: PuttsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ putts }) => {
      this.updateForm(putts);
    });
  }

  updateForm(putts: IPutts) {
    this.editForm.patchValue({
      id: putts.id,
      playerId: putts.playerId,
      tournamentId: putts.tournamentId,
      courseId: putts.courseId,
      hole1: putts.hole1,
      hole2: putts.hole2,
      hole3: putts.hole3,
      hole4: putts.hole4,
      hole5: putts.hole5,
      hole6: putts.hole6,
      hole7: putts.hole7,
      hole8: putts.hole8,
      hole9: putts.hole9,
      hole10: putts.hole10,
      hole11: putts.hole11,
      hole12: putts.hole12,
      hole13: putts.hole13,
      hole14: putts.hole14,
      hole15: putts.hole15,
      hole16: putts.hole16,
      hole17: putts.hole17,
      hole18: putts.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const putts = this.createFromForm();
    if (putts.id !== undefined) {
      this.subscribeToSaveResponse(this.puttsService.update(putts));
    } else {
      this.subscribeToSaveResponse(this.puttsService.create(putts));
    }
  }

  private createFromForm(): IPutts {
    return {
      ...new Putts(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPutts>>) {
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
