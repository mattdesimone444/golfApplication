import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPuttingAnalysis } from 'app/shared/model/putting-analysis.model';
import { PuttingAnalysisService } from './putting-analysis.service';

@Component({
  selector: 'jhi-putting-analysis-delete-dialog',
  templateUrl: './putting-analysis-delete-dialog.component.html'
})
export class PuttingAnalysisDeleteDialogComponent {
  puttingAnalysis: IPuttingAnalysis;

  constructor(
    protected puttingAnalysisService: PuttingAnalysisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.puttingAnalysisService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'puttingAnalysisListModification',
        content: 'Deleted an puttingAnalysis'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-putting-analysis-delete-popup',
  template: ''
})
export class PuttingAnalysisDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ puttingAnalysis }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PuttingAnalysisDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.puttingAnalysis = puttingAnalysis;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/putting-analysis', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/putting-analysis', { outlets: { popup: null } }]);
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
