import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPutts } from 'app/shared/model/putts.model';
import { PuttsService } from './putts.service';

@Component({
  selector: 'jhi-putts-delete-dialog',
  templateUrl: './putts-delete-dialog.component.html'
})
export class PuttsDeleteDialogComponent {
  putts: IPutts;

  constructor(protected puttsService: PuttsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.puttsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'puttsListModification',
        content: 'Deleted an putts'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-putts-delete-popup',
  template: ''
})
export class PuttsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ putts }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PuttsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.putts = putts;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/putts', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/putts', { outlets: { popup: null } }]);
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
