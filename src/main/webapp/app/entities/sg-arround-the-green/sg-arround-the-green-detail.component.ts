import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';

@Component({
  selector: 'jhi-sg-arround-the-green-detail',
  templateUrl: './sg-arround-the-green-detail.component.html'
})
export class SGArroundTheGreenDetailComponent implements OnInit {
  sGArroundTheGreen: ISGArroundTheGreen;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGArroundTheGreen }) => {
      this.sGArroundTheGreen = sGArroundTheGreen;
    });
  }

  previousState() {
    window.history.back();
  }
}
