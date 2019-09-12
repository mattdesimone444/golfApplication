import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGolfer, Golfer } from 'app/shared/model/golfer.model';
import { GolferService } from './golfer.service';

@Component({
  selector: 'jhi-golfer-update',
  templateUrl: './golfer-update.component.html'
})
export class GolferUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    height: [],
    weight: [],
    age: [],
    residenceCity: [],
    residenceState: [],
    playsFromCity: [],
    playsFromState: [],
    turnedPro: []
  });

  constructor(protected golferService: GolferService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ golfer }) => {
      this.updateForm(golfer);
    });
  }

  updateForm(golfer: IGolfer) {
    this.editForm.patchValue({
      id: golfer.id,
      name: golfer.name,
      height: golfer.height,
      weight: golfer.weight,
      age: golfer.age,
      residenceCity: golfer.residenceCity,
      residenceState: golfer.residenceState,
      playsFromCity: golfer.playsFromCity,
      playsFromState: golfer.playsFromState,
      turnedPro: golfer.turnedPro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const golfer = this.createFromForm();
    if (golfer.id !== undefined) {
      this.subscribeToSaveResponse(this.golferService.update(golfer));
    } else {
      this.subscribeToSaveResponse(this.golferService.create(golfer));
    }
  }

  private createFromForm(): IGolfer {
    return {
      ...new Golfer(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      height: this.editForm.get(['height']).value,
      weight: this.editForm.get(['weight']).value,
      age: this.editForm.get(['age']).value,
      residenceCity: this.editForm.get(['residenceCity']).value,
      residenceState: this.editForm.get(['residenceState']).value,
      playsFromCity: this.editForm.get(['playsFromCity']).value,
      playsFromState: this.editForm.get(['playsFromState']).value,
      turnedPro: this.editForm.get(['turnedPro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGolfer>>) {
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
