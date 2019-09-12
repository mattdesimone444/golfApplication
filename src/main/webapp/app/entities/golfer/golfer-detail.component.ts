import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGolfer } from 'app/shared/model/golfer.model';

@Component({
  selector: 'jhi-golfer-detail',
  templateUrl: './golfer-detail.component.html'
})
export class GolferDetailComponent implements OnInit {
  golfer: IGolfer;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ golfer }) => {
      this.golfer = golfer;
    });
  }

  previousState() {
    window.history.back();
  }
}
