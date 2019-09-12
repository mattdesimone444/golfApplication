import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISandSaves, SandSaves } from 'app/shared/model/sand-saves.model';
import { SandSavesService } from './sand-saves.service';

@Component({
  selector: 'jhi-sand-saves-update',
  templateUrl: './sand-saves-update.component.html'
})
export class SandSavesUpdateComponent implements OnInit {
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

  constructor(protected sandSavesService: SandSavesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sandSaves }) => {
      this.updateForm(sandSaves);
    });
  }

  updateForm(sandSaves: ISandSaves) {
    this.editForm.patchValue({
      id: sandSaves.id,
      playerId: sandSaves.playerId,
      tournamentId: sandSaves.tournamentId,
      courseId: sandSaves.courseId,
      hole1: sandSaves.hole1,
      hole2: sandSaves.hole2,
      hole3: sandSaves.hole3,
      hole4: sandSaves.hole4,
      hole5: sandSaves.hole5,
      hole6: sandSaves.hole6,
      hole7: sandSaves.hole7,
      hole8: sandSaves.hole8,
      hole9: sandSaves.hole9,
      hole10: sandSaves.hole10,
      hole11: sandSaves.hole11,
      hole12: sandSaves.hole12,
      hole13: sandSaves.hole13,
      hole14: sandSaves.hole14,
      hole15: sandSaves.hole15,
      hole16: sandSaves.hole16,
      hole17: sandSaves.hole17,
      hole18: sandSaves.hole18
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sandSaves = this.createFromForm();
    if (sandSaves.id !== undefined) {
      this.subscribeToSaveResponse(this.sandSavesService.update(sandSaves));
    } else {
      this.subscribeToSaveResponse(this.sandSavesService.create(sandSaves));
    }
  }

  private createFromForm(): ISandSaves {
    return {
      ...new SandSaves(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISandSaves>>) {
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
