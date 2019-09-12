import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';
import { ApproachShotDistanceToPinService } from './approach-shot-distance-to-pin.service';

@Component({
  selector: 'jhi-approach-shot-distance-to-pin-delete-dialog',
  templateUrl: './approach-shot-distance-to-pin-delete-dialog.component.html'
})
export class ApproachShotDistanceToPinDeleteDialogComponent {
  approachShotDistanceToPin: IApproachShotDistanceToPin;

  constructor(
    protected approachShotDistanceToPinService: ApproachShotDistanceToPinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.approachShotDistanceToPinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'approachShotDistanceToPinListModification',
        content: 'Deleted an approachShotDistanceToPin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-approach-shot-distance-to-pin-delete-popup',
  template: ''
})
export class ApproachShotDistanceToPinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ approachShotDistanceToPin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApproachShotDistanceToPinDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.approachShotDistanceToPin = approachShotDistanceToPin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/approach-shot-distance-to-pin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/approach-shot-distance-to-pin', { outlets: { popup: null } }]);
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
