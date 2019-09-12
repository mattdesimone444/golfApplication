import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISGTotal } from 'app/shared/model/sg-total.model';

@Component({
  selector: 'jhi-sg-total-detail',
  templateUrl: './sg-total-detail.component.html'
})
export class SGTotalDetailComponent implements OnInit {
  sGTotal: ISGTotal;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGTotal }) => {
      this.sGTotal = sGTotal;
    });
  }

  previousState() {
    window.history.back();
  }
}
