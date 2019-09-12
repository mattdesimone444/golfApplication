import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';

@Component({
  selector: 'jhi-approach-shot-distance-to-pin-detail',
  templateUrl: './approach-shot-distance-to-pin-detail.component.html'
})
export class ApproachShotDistanceToPinDetailComponent implements OnInit {
  approachShotDistanceToPin: IApproachShotDistanceToPin;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ approachShotDistanceToPin }) => {
      this.approachShotDistanceToPin = approachShotDistanceToPin;
    });
  }

  previousState() {
    window.history.back();
  }
}
