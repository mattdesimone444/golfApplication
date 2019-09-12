import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDrivingDistance } from 'app/shared/model/driving-distance.model';
import { DrivingDistanceService } from './driving-distance.service';

@Component({
  selector: 'jhi-driving-distance-delete-dialog',
  templateUrl: './driving-distance-delete-dialog.component.html'
})
export class DrivingDistanceDeleteDialogComponent {
  drivingDistance: IDrivingDistance;

  constructor(
    protected drivingDistanceService: DrivingDistanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.drivingDistanceService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'drivingDistanceListModification',
        content: 'Deleted an drivingDistance'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-driving-distance-delete-popup',
  template: ''
})
export class DrivingDistanceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ drivingDistance }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DrivingDistanceDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.drivingDistance = drivingDistance;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/driving-distance', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/driving-distance', { outlets: { popup: null } }]);
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
