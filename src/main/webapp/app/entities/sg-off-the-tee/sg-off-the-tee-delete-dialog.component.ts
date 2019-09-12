import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';
import { SGOffTheTeeService } from './sg-off-the-tee.service';

@Component({
  selector: 'jhi-sg-off-the-tee-delete-dialog',
  templateUrl: './sg-off-the-tee-delete-dialog.component.html'
})
export class SGOffTheTeeDeleteDialogComponent {
  sGOffTheTee: ISGOffTheTee;

  constructor(
    protected sGOffTheTeeService: SGOffTheTeeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sGOffTheTeeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sGOffTheTeeListModification',
        content: 'Deleted an sGOffTheTee'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sg-off-the-tee-delete-popup',
  template: ''
})
export class SGOffTheTeeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sGOffTheTee }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SGOffTheTeeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sGOffTheTee = sGOffTheTee;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sg-off-the-tee', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sg-off-the-tee', { outlets: { popup: null } }]);
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
