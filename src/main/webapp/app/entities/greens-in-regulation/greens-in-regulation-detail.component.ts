import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGreensInRegulation } from 'app/shared/model/greens-in-regulation.model';

@Component({
  selector: 'jhi-greens-in-regulation-detail',
  templateUrl: './greens-in-regulation-detail.component.html'
})
export class GreensInRegulationDetailComponent implements OnInit {
  greensInRegulation: IGreensInRegulation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ greensInRegulation }) => {
      this.greensInRegulation = greensInRegulation;
    });
  }

  previousState() {
    window.history.back();
  }
}
