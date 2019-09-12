import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';

@Component({
  selector: 'jhi-sg-tee-to-green-detail',
  templateUrl: './sg-tee-to-green-detail.component.html'
})
export class SGTeeToGreenDetailComponent implements OnInit {
  sGTeeToGreen: ISGTeeToGreen;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGTeeToGreen }) => {
      this.sGTeeToGreen = sGTeeToGreen;
    });
  }

  previousState() {
    window.history.back();
  }
}
