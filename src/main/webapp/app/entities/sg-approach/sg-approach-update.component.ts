import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISGApproach, SGApproach } from 'app/shared/model/sg-approach.model';
import { SGApproachService } from './sg-approach.service';

@Component({
  selector: 'jhi-sg-approach-update',
  templateUrl: './sg-approach-update.component.html'
})
export class SGApproachUpdateComponent implements OnInit {
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

  constructor(protected sGApproachService: SGApproachService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sGApproach }) => {
      this.updateForm(sGApproach);
    });
  }

  updateForm(sGApproach: ISGApproach) {
    this.editForm.patchValue({
      id: sGApproach.id,
      playerId: sGApproach.playerId,
      tournamentId: sGApproach.tournamentId,
      courseId: sGApproach.courseId,
      hole1: sGApproach.hole1,
      hole2: sGApproach.hole2,
      hole3: sGApproach.hole3,
      hole4: sGApproach.hole4,
      hole5: sGApproach.hole5,
      hole6: sGApproach.hole6,
      hole7: sGApproach.hole7,
      hole8: sGApproach.hole8,
      hole9: sGApproach.hole9,
      hole10: sGApproach.hole10,
      hole11: sGApproach.hole11,
      hole12: sGApproach.hole12,
      hole13: sGApproach.hole13,
      hole14: sGApproach.hole14,
      hole15: sGApproach.hole15,
      hole16: sGApproach.hole16,
      hole17: sGApproach.hole17,
      hole18: sGApproach.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sGApproach = this.createFromForm();
    if (sGApproach.id !== undefined) {
      this.subscribeToSaveResponse(this.sGApproachService.update(sGApproach));
    } else {
      this.subscribeToSaveResponse(this.sGApproachService.create(sGApproach));
    }
  }

  private createFromForm(): ISGApproach {
    return {
      ...new SGApproach(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISGApproach>>) {
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
