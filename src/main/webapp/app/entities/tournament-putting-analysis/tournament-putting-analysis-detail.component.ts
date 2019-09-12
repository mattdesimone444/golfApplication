import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';

@Component({
  selector: 'jhi-tournament-putting-analysis-detail',
  templateUrl: './tournament-putting-analysis-detail.component.html'
})
export class TournamentPuttingAnalysisDetailComponent implements OnInit {
  tournamentPuttingAnalysis: ITournamentPuttingAnalysis;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tournamentPuttingAnalysis }) => {
      this.tournamentPuttingAnalysis = tournamentPuttingAnalysis;
    });
  }

  previousState() {
    window.history.back();
  }
}
