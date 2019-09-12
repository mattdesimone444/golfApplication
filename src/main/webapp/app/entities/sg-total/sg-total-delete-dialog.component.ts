import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISGTotal } from 'app/shared/model/sg-total.model';
import { SGTotalService } from './sg-total.service';

@Component({
  selector: 'jhi-sg-total-delete-dialog',
  templateUrl: './sg-total-delete-dialog.component.html'
})
export class SGTotalDeleteDialogComponent {
  sGTotal: ISGTotal;

  constructor(protected sGTotalService: SGTotalService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sGTotalService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sGTotalListModification',
        content: 'Deleted an sGTotal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sg-total-delete-popup',
  template: ''
})
export class SGTotalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGTotal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SGTotalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sGTotal = sGTotal;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sg-total', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sg-total', { outlets: { popup: null } }]);
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
