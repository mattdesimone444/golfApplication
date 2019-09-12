import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

@Component({
  selector: 'jhi-sg-off-the-tee-detail',
  templateUrl: './sg-off-the-tee-detail.component.html'
})
export class SGOffTheTeeDetailComponent implements OnInit {
  sGOffTheTee: ISGOffTheTee;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGOffTheTee }) => {
      this.sGOffTheTee = sGOffTheTee;
    });
  }

  previousState() {
    window.history.back();
  }
}
