import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';
import { PuttsMadeDistanceService } from './putts-made-distance.service';

@Component({
  selector: 'jhi-putts-made-distance-delete-dialog',
  templateUrl: './putts-made-distance-delete-dialog.component.html'
})
export class PuttsMadeDistanceDeleteDialogComponent {
  puttsMadeDistance: IPuttsMadeDistance;

  constructor(
    protected puttsMadeDistanceService: PuttsMadeDistanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.puttsMadeDistanceService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'puttsMadeDistanceListModification',
        content: 'Deleted an puttsMadeDistance'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-putts-made-distance-delete-popup',
  template: ''
})
export class PuttsMadeDistanceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ puttsMadeDistance }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PuttsMadeDistanceDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.puttsMadeDistance = puttsMadeDistance;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/putts-made-distance', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/putts-made-distance', { outlets: { popup: null } }]);
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
