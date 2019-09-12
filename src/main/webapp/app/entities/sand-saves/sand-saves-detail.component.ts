import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISandSaves } from 'app/shared/model/sand-saves.model';

@Component({
  selector: 'jhi-sand-saves-detail',
  templateUrl: './sand-saves-detail.component.html'
})
export class SandSavesDetailComponent implements OnInit {
  sandSaves: ISandSaves;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sandSaves }) => {
      this.sandSaves = sandSaves;
    });
  }

  previousState() {
    window.history.back();
  }
}
