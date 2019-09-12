import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFairwaysHit } from 'app/shared/model/fairways-hit.model';

@Component({
  selector: 'jhi-fairways-hit-detail',
  templateUrl: './fairways-hit-detail.component.html'
})
export class FairwaysHitDetailComponent implements OnInit {
  fairwaysHit: IFairwaysHit;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fairwaysHit }) => {
      this.fairwaysHit = fairwaysHit;
    });
  }

  previousState() {
    window.history.back();
  }
}
