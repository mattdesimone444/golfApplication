import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGreensInRegulation, GreensInRegulation } from 'app/shared/model/greens-in-regulation.model';
import { GreensInRegulationService } from './greens-in-regulation.service';

@Component({
  selector: 'jhi-greens-in-regulation-update',
  templateUrl: './greens-in-regulation-update.component.html'
})
export class GreensInRegulationUpdateComponent implements OnInit {
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
    protected greensInRegulationService: GreensInRegulationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ greensInRegulation }) => {
      this.updateForm(greensInRegulation);
    });
  }

  updateForm(greensInRegulation: IGreensInRegulation) {
    this.editForm.patchValue({
      id: greensInRegulation.id,
      playerId: greensInRegulation.playerId,
      tournamentId: greensInRegulation.tournamentId,
      courseId: greensInRegulation.courseId,
      hole1: greensInRegulation.hole1,
      hole2: greensInRegulation.hole2,
      hole3: greensInRegulation.hole3,
      hole4: greensInRegulation.hole4,
      hole5: greensInRegulation.hole5,
      hole6: greensInRegulation.hole6,
      hole7: greensInRegulation.hole7,
      hole8: greensInRegulation.hole8,
      hole9: greensInRegulation.hole9,
      hole10: greensInRegulation.hole10,
      hole11: greensInRegulation.hole11,
      hole12: greensInRegulation.hole12,
      hole13: greensInRegulation.hole13,
      hole14: greensInRegulation.hole14,
      hole15: greensInRegulation.hole15,
      hole16: greensInRegulation.hole16,
      hole17: greensInRegulation.hole17,
      hole18: greensInRegulation.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const greensInRegulation = this.createFromForm();
    if (greensInRegulation.id !== undefined) {
      this.subscribeToSaveResponse(this.greensInRegulationService.update(greensInRegulation));
    } else {
      this.subscribeToSaveResponse(this.greensInRegulationService.create(greensInRegulation));
    }
  }

  private createFromForm(): IGreensInRegulation {
    return {
      ...new GreensInRegulation(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGreensInRegulation>>) {
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
