import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';
import { TournamentPuttingAnalysisService } from './tournament-putting-analysis.service';

@Component({
  selector: 'jhi-tournament-putting-analysis-delete-dialog',
  templateUrl: './tournament-putting-analysis-delete-dialog.component.html'
})
export class TournamentPuttingAnalysisDeleteDialogComponent {
  tournamentPuttingAnalysis: ITournamentPuttingAnalysis;

  constructor(
    protected tournamentPuttingAnalysisService: TournamentPuttingAnalysisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tournamentPuttingAnalysisService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tournamentPuttingAnalysisListModification',
        content: 'Deleted an tournamentPuttingAnalysis'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tournament-putting-analysis-delete-popup',
  template: ''
})
export class TournamentPuttingAnalysisDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tournamentPuttingAnalysis }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TournamentPuttingAnalysisDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.tournamentPuttingAnalysis = tournamentPuttingAnalysis;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tournament-putting-analysis', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tournament-putting-analysis', { outlets: { popup: null } }]);
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
