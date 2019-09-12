import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGreensInRegulation } from 'app/shared/model/greens-in-regulation.model';
import { GreensInRegulationService } from './greens-in-regulation.service';

@Component({
  selector: 'jhi-greens-in-regulation-delete-dialog',
  templateUrl: './greens-in-regulation-delete-dialog.component.html'
})
export class GreensInRegulationDeleteDialogComponent {
  greensInRegulation: IGreensInRegulation;

  constructor(
    protected greensInRegulationService: GreensInRegulationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.greensInRegulationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'greensInRegulationListModification',
        content: 'Deleted an greensInRegulation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-greens-in-regulation-delete-popup',
  template: ''
})
export class GreensInRegulationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ greensInRegulation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GreensInRegulationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.greensInRegulation = greensInRegulation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/greens-in-regulation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/greens-in-regulation', { outlets: { popup: null } }]);
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
