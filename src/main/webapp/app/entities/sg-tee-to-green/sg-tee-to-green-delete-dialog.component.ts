import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';
import { SGTeeToGreenService } from './sg-tee-to-green.service';

@Component({
  selector: 'jhi-sg-tee-to-green-delete-dialog',
  templateUrl: './sg-tee-to-green-delete-dialog.component.html'
})
export class SGTeeToGreenDeleteDialogComponent {
  sGTeeToGreen: ISGTeeToGreen;

  constructor(
    protected sGTeeToGreenService: SGTeeToGreenService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sGTeeToGreenService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sGTeeToGreenListModification',
        content: 'Deleted an sGTeeToGreen'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sg-tee-to-green-delete-popup',
  template: ''
})
export class SGTeeToGreenDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGTeeToGreen }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SGTeeToGreenDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sGTeeToGreen = sGTeeToGreen;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sg-tee-to-green', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sg-tee-to-green', { outlets: { popup: null } }]);
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
