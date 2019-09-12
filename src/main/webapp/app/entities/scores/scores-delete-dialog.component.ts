import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScores } from 'app/shared/model/scores.model';
import { ScoresService } from './scores.service';

@Component({
  selector: 'jhi-scores-delete-dialog',
  templateUrl: './scores-delete-dialog.component.html'
})
export class ScoresDeleteDialogComponent {
  scores: IScores;

  constructor(protected scoresService: ScoresService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.scoresService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'scoresListModification',
        content: 'Deleted an scores'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-scores-delete-popup',
  template: ''
})
export class ScoresDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ scores }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ScoresDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.scores = scores;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/scores', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/scores', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
