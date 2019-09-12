import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPuttingAnalysis, PuttingAnalysis } from 'app/shared/model/putting-analysis.model';
import { PuttingAnalysisService } from './putting-analysis.service';

@Component({
  selector: 'jhi-putting-analysis-update',
  templateUrl: './putting-analysis-update.component.html'
})
export class PuttingAnalysisUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    puttinAnalysisId: [],
    longest: [],
    total: [],
    lessThree: [],
    lessTen: [],
    threeToFive: [],
    fiveToSeven: [],
    sevenToTen: [],
    fourToEight: [],
    tenToFifteen: [],
    fifteenToTwenty: [],
    twentyToTwentyFive: [],
    lessTwentyFive: []
  });

  constructor(
    protected puttingAnalysisService: PuttingAnalysisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ puttingAnalysis }) => {
      this.updateForm(puttingAnalysis);
    });
  }

  updateForm(puttingAnalysis: IPuttingAnalysis) {
    this.editForm.patchValue({
      id: puttingAnalysis.id,
      puttinAnalysisId: puttingAnalysis.puttinAnalysisId,
      longest: puttingAnalysis.longest,
      total: puttingAnalysis.total,
      lessThree: puttingAnalysis.lessThree,
      lessTen: puttingAnalysis.lessTen,
      threeToFive: puttingAnalysis.threeToFive,
      fiveToSeven: puttingAnalysis.fiveToSeven,
      sevenToTen: puttingAnalysis.sevenToTen,
      fourToEight: puttingAnalysis.fourToEight,
      tenToFifteen: puttingAnalysis.tenToFifteen,
      fifteenToTwenty: puttingAnalysis.fifteenToTwenty,
      twentyToTwentyFive: puttingAnalysis.twentyToTwentyFive,
      lessTwentyFive: puttingAnalysis.lessTwentyFive
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const puttingAnalysis = this.createFromForm();
    if (puttingAnalysis.id !== undefined) {
      this.subscribeToSaveResponse(this.puttingAnalysisService.update(puttingAnalysis));
    } else {
      this.subscribeToSaveResponse(this.puttingAnalysisService.create(puttingAnalysis));
    }
  }

  private createFromForm(): IPuttingAnalysis {
    return {
      ...new PuttingAnalysis(),
      id: this.editForm.get(['id']).value,
      puttinAnalysisId: this.editForm.get(['puttinAnalysisId']).value,
      longest: this.editForm.get(['longest']).value,
      total: this.editForm.get(['total']).value,
      lessThree: this.editForm.get(['lessThree']).value,
      lessTen: this.editForm.get(['lessTen']).value,
      threeToFive: this.editForm.get(['threeToFive']).value,
      fiveToSeven: this.editForm.get(['fiveToSeven']).value,
      sevenToTen: this.editForm.get(['sevenToTen']).value,
      fourToEight: this.editForm.get(['fourToEight']).value,
      tenToFifteen: this.editForm.get(['tenToFifteen']).value,
      fifteenToTwenty: this.editForm.get(['fifteenToTwenty']).value,
      twentyToTwentyFive: this.editForm.get(['twentyToTwentyFive']).value,
      lessTwentyFive: this.editForm.get(['lessTwentyFive']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPuttingAnalysis>>) {
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
