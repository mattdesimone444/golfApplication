import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { ITournament, Tournament } from 'app/shared/model/tournament.model';
import { TournamentService } from './tournament.service';

@Component({
  selector: 'jhi-tournament-update',
  templateUrl: './tournament-update.component.html'
})
export class TournamentUpdateComponent implements OnInit {
  isSaving: boolean;
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [],
    season: [],
    startDate: [],
    endDate: [],
    purse: [],
    courseId: []
  });

  constructor(protected tournamentService: TournamentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tournament }) => {
      this.updateForm(tournament);
    });
  }

  updateForm(tournament: ITournament) {
    this.editForm.patchValue({
      id: tournament.id,
      name: tournament.name,
      season: tournament.season,
      startDate: tournament.startDate,
      endDate: tournament.endDate,
      purse: tournament.purse,
      courseId: tournament.courseId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tournament = this.createFromForm();
    if (tournament.id !== undefined) {
      this.subscribeToSaveResponse(this.tournamentService.update(tournament));
    } else {
      this.subscribeToSaveResponse(this.tournamentService.create(tournament));
    }
  }

  private createFromForm(): ITournament {
    return {
      ...new Tournament(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      season: this.editForm.get(['season']).value,
      startDate: this.editForm.get(['startDate']).value,
      endDate: this.editForm.get(['endDate']).value,
      purse: this.editForm.get(['purse']).value,
      courseId: this.editForm.get(['courseId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITournament>>) {
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
