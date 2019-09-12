import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDrivingDistance } from 'app/shared/model/driving-distance.model';

@Component({
  selector: 'jhi-driving-distance-detail',
  templateUrl: './driving-distance-detail.component.html'
})
export class DrivingDistanceDetailComponent implements OnInit {
  drivingDistance: IDrivingDistance;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ drivingDistance }) => {
      this.drivingDistance = drivingDistance;
    });
  }

  previousState() {
    window.history.back();
  }
}
