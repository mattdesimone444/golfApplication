import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGolfer } from 'app/shared/model/golfer.model';
import { GolferService } from './golfer.service';

@Component({
  selector: 'jhi-golfer-delete-dialog',
  templateUrl: './golfer-delete-dialog.component.html'
})
export class GolferDeleteDialogComponent {
  golfer: IGolfer;

  constructor(protected golferService: GolferService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.golferService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'golferListModification',
        content: 'Deleted an golfer'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-golfer-delete-popup',
  template: ''
})
export class GolferDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ golfer }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GolferDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.golfer = golfer;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/golfer', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/golfer', { outlets: { popup: null } }]);
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
