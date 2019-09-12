import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISGApproach } from 'app/shared/model/sg-approach.model';
import { SGApproachService } from './sg-approach.service';

@Component({
  selector: 'jhi-sg-approach-delete-dialog',
  templateUrl: './sg-approach-delete-dialog.component.html'
})
export class SGApproachDeleteDialogComponent {
  sGApproach: ISGApproach;

  constructor(
    protected sGApproachService: SGApproachService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sGApproachService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sGApproachListModification',
        content: 'Deleted an sGApproach'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sg-approach-delete-popup',
  template: ''
})
export class SGApproachDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGApproach }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SGApproachDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sGApproach = sGApproach;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sg-approach', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sg-approach', { outlets: { popup: null } }]);
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
