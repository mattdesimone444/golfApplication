import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';

@Component({
  selector: 'jhi-putts-made-distance-detail',
  templateUrl: './putts-made-distance-detail.component.html'
})
export class PuttsMadeDistanceDetailComponent implements OnInit {
  puttsMadeDistance: IPuttsMadeDistance;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ puttsMadeDistance }) => {
      this.puttsMadeDistance = puttsMadeDistance;
    });
  }

  previousState() {
    window.history.back();
  }
}
