import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScores } from 'app/shared/model/scores.model';

@Component({
  selector: 'jhi-scores-detail',
  templateUrl: './scores-detail.component.html'
})
export class ScoresDetailComponent implements OnInit {
  scores: IScores;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ scores }) => {
      this.scores = scores;
    });
  }

  previousState() {
    window.history.back();
  }
}
