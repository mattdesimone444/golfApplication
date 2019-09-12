import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFairwaysHit } from 'app/shared/model/fairways-hit.model';
import { FairwaysHitService } from './fairways-hit.service';

@Component({
  selector: 'jhi-fairways-hit-delete-dialog',
  templateUrl: './fairways-hit-delete-dialog.component.html'
})
export class FairwaysHitDeleteDialogComponent {
  fairwaysHit: IFairwaysHit;

  constructor(
    protected fairwaysHitService: FairwaysHitService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.fairwaysHitService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'fairwaysHitListModification',
        content: 'Deleted an fairwaysHit'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-fairways-hit-delete-popup',
  template: ''
})
export class FairwaysHitDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ fairwaysHit }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(FairwaysHitDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.fairwaysHit = fairwaysHit;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/fairways-hit', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/fairways-hit', { outlets: { popup: null } }]);
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
