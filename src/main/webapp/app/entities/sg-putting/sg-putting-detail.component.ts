import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISGPutting } from 'app/shared/model/sg-putting.model';

@Component({
  selector: 'jhi-sg-putting-detail',
  templateUrl: './sg-putting-detail.component.html'
})
export class SGPuttingDetailComponent implements OnInit {
  sGPutting: ISGPutting;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGPutting }) => {
      this.sGPutting = sGPutting;
    });
  }

  previousState() {
    window.history.back();
  }
}
