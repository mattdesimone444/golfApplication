import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITournamentPuttingAnalysis, TournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';
import { TournamentPuttingAnalysisService } from './tournament-putting-analysis.service';

@Component({
  selector: 'jhi-tournament-putting-analysis-update',
  templateUrl: './tournament-putting-analysis-update.component.html'
})
export class TournamentPuttingAnalysisUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    playerId: [],
    tournamentId: [],
    courseId: [],
    roundOneId: [],
    roundTwoId: [],
    roundThreeId: [],
    roundFourId: []
  });

  constructor(
    protected tournamentPuttingAnalysisService: TournamentPuttingAnalysisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tournamentPuttingAnalysis }) => {
      this.updateForm(tournamentPuttingAnalysis);
    });
  }

  updateForm(tournamentPuttingAnalysis: ITournamentPuttingAnalysis) {
    this.editForm.patchValue({
      id: tournamentPuttingAnalysis.id,
      playerId: tournamentPuttingAnalysis.playerId,
      tournamentId: tournamentPuttingAnalysis.tournamentId,
      courseId: tournamentPuttingAnalysis.courseId,
      roundOneId: tournamentPuttingAnalysis.roundOneId,
      roundTwoId: tournamentPuttingAnalysis.roundTwoId,
      roundThreeId: tournamentPuttingAnalysis.roundThreeId,
      roundFourId: tournamentPuttingAnalysis.roundFourId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tournamentPuttingAnalysis = this.createFromForm();
    if (tournamentPuttingAnalysis.id !== undefined) {
      this.subscribeToSaveResponse(this.tournamentPuttingAnalysisService.update(tournamentPuttingAnalysis));
    } else {
      this.subscribeToSaveResponse(this.tournamentPuttingAnalysisService.create(tournamentPuttingAnalysis));
    }
  }

  private createFromForm(): ITournamentPuttingAnalysis {
    return {
      ...new TournamentPuttingAnalysis(),
      id: this.editForm.get(['id']).value,
      playerId: this.editForm.get(['playerId']).value,
      tournamentId: this.editForm.get(['tournamentId']).value,
      courseId: this.editForm.get(['courseId']).value,
      roundOneId: this.editForm.get(['roundOneId']).value,
      roundTwoId: this.editForm.get(['roundTwoId']).value,
      roundThreeId: this.editForm.get(['roundThreeId']).value,
      roundFourId: this.editForm.get(['roundFourId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITournamentPuttingAnalysis>>) {
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
