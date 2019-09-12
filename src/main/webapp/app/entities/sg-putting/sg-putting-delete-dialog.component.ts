import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISGPutting } from 'app/shared/model/sg-putting.model';
import { SGPuttingService } from './sg-putting.service';

@Component({
  selector: 'jhi-sg-putting-delete-dialog',
  templateUrl: './sg-putting-delete-dialog.component.html'
})
export class SGPuttingDeleteDialogComponent {
  sGPutting: ISGPutting;

  constructor(protected sGPuttingService: SGPuttingService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sGPuttingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sGPuttingListModification',
        content: 'Deleted an sGPutting'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sg-putting-delete-popup',
  template: ''
})
export class SGPuttingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGPutting }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SGPuttingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sGPutting = sGPutting;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sg-putting', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sg-putting', { outlets: { popup: null } }]);
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
