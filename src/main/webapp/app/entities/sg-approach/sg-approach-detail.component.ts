import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISGApproach } from 'app/shared/model/sg-approach.model';

@Component({
  selector: 'jhi-sg-approach-detail',
  templateUrl: './sg-approach-detail.component.html'
})
export class SGApproachDetailComponent implements OnInit {
  sGApproach: ISGApproach;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGApproach }) => {
      this.sGApproach = sGApproach;
    });
  }

  previousState() {
    window.history.back();
  }
}
