import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDistanceOfApproach } from 'app/shared/model/distance-of-approach.model';
import { DistanceOfApproachService } from './distance-of-approach.service';

@Component({
  selector: 'jhi-distance-of-approach-delete-dialog',
  templateUrl: './distance-of-approach-delete-dialog.component.html'
})
export class DistanceOfApproachDeleteDialogComponent {
  distanceOfApproach: IDistanceOfApproach;

  constructor(
    protected distanceOfApproachService: DistanceOfApproachService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.distanceOfApproachService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'distanceOfApproachListModification',
        content: 'Deleted an distanceOfApproach'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-distance-of-approach-delete-popup',
  template: ''
})
export class DistanceOfApproachDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ distanceOfApproach }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DistanceOfApproachDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.distanceOfApproach = distanceOfApproach;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/distance-of-approach', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/distance-of-approach', { outlets: { popup: null } }]);
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
