import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISGOffTheTee, SGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';
import { SGOffTheTeeService } from './sg-off-the-tee.service';

@Component({
  selector: 'jhi-sg-off-the-tee-update',
  templateUrl: './sg-off-the-tee-update.component.html'
})
export class SGOffTheTeeUpdateComponent implements OnInit {
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

  constructor(protected sGOffTheTeeService: SGOffTheTeeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sGOffTheTee }) => {
      this.updateForm(sGOffTheTee);
    });
  }

  updateForm(sGOffTheTee: ISGOffTheTee) {
    this.editForm.patchValue({
      id: sGOffTheTee.id,
      playerId: sGOffTheTee.playerId,
      tournamentId: sGOffTheTee.tournamentId,
      courseId: sGOffTheTee.courseId,
      hole1: sGOffTheTee.hole1,
      hole2: sGOffTheTee.hole2,
      hole3: sGOffTheTee.hole3,
      hole4: sGOffTheTee.hole4,
      hole5: sGOffTheTee.hole5,
      hole6: sGOffTheTee.hole6,
      hole7: sGOffTheTee.hole7,
      hole8: sGOffTheTee.hole8,
      hole9: sGOffTheTee.hole9,
      hole10: sGOffTheTee.hole10,
      hole11: sGOffTheTee.hole11,
      hole12: sGOffTheTee.hole12,
      hole13: sGOffTheTee.hole13,
      hole14: sGOffTheTee.hole14,
      hole15: sGOffTheTee.hole15,
      hole16: sGOffTheTee.hole16,
      hole17: sGOffTheTee.hole17,
      hole18: sGOffTheTee.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sGOffTheTee = this.createFromForm();
    if (sGOffTheTee.id !== undefined) {
      this.subscribeToSaveResponse(this.sGOffTheTeeService.update(sGOffTheTee));
    } else {
      this.subscribeToSaveResponse(this.sGOffTheTeeService.create(sGOffTheTee));
    }
  }

  private createFromForm(): ISGOffTheTee {
    return {
      ...new SGOffTheTee(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISGOffTheTee>>) {
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
