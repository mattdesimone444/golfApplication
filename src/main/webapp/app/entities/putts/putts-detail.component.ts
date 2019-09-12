import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPutts } from 'app/shared/model/putts.model';

@Component({
  selector: 'jhi-putts-detail',
  templateUrl: './putts-detail.component.html'
})
export class PuttsDetailComponent implements OnInit {
  putts: IPutts;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ putts }) => {
      this.putts = putts;
    });
  }

  previousState() {
    window.history.back();
  }
}
