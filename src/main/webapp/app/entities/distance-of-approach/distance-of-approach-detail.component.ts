import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDistanceOfApproach } from 'app/shared/model/distance-of-approach.model';

@Component({
  selector: 'jhi-distance-of-approach-detail',
  templateUrl: './distance-of-approach-detail.component.html'
})
export class DistanceOfApproachDetailComponent implements OnInit {
  distanceOfApproach: IDistanceOfApproach;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ distanceOfApproach }) => {
      this.distanceOfApproach = distanceOfApproach;
    });
  }

  previousState() {
    window.history.back();
  }
}
