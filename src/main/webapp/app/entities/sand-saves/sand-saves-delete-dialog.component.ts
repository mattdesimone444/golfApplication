import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISandSaves } from 'app/shared/model/sand-saves.model';
import { SandSavesService } from './sand-saves.service';

@Component({
  selector: 'jhi-sand-saves-delete-dialog',
  templateUrl: './sand-saves-delete-dialog.component.html'
})
export class SandSavesDeleteDialogComponent {
  sandSaves: ISandSaves;

  constructor(protected sandSavesService: SandSavesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sandSavesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sandSavesListModification',
        content: 'Deleted an sandSaves'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sand-saves-delete-popup',
  template: ''
})
export class SandSavesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sandSaves }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SandSavesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sandSaves = sandSaves;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sand-saves', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sand-saves', { outlets: { popup: null } }]);
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
