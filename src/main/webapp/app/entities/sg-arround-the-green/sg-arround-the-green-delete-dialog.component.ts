import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';
import { SGArroundTheGreenService } from './sg-arround-the-green.service';

@Component({
  selector: 'jhi-sg-arround-the-green-delete-dialog',
  templateUrl: './sg-arround-the-green-delete-dialog.component.html'
})
export class SGArroundTheGreenDeleteDialogComponent {
  sGArroundTheGreen: ISGArroundTheGreen;

  constructor(
    protected sGArroundTheGreenService: SGArroundTheGreenService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sGArroundTheGreenService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sGArroundTheGreenListModification',
        content: 'Deleted an sGArroundTheGreen'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sg-arround-the-green-delete-popup',
  template: ''
})
export class SGArroundTheGreenDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGArroundTheGreen }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SGArroundTheGreenDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sGArroundTheGreen = sGArroundTheGreen;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sg-arround-the-green', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sg-arround-the-green', { outlets: { popup: null } }]);
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
