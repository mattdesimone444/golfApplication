import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPuttingAnalysis } from 'app/shared/model/putting-analysis.model';

@Component({
  selector: 'jhi-putting-analysis-detail',
  templateUrl: './putting-analysis-detail.component.html'
})
export class PuttingAnalysisDetailComponent implements OnInit {
  puttingAnalysis: IPuttingAnalysis;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ puttingAnalysis }) => {
      this.puttingAnalysis = puttingAnalysis;
    });
  }

  previousState() {
    window.history.back();
  }
}
