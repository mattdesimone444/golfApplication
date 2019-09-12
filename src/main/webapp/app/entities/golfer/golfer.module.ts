import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  GolferComponent,
  GolferDetailComponent,
  GolferUpdateComponent,
  GolferDeletePopupComponent,
  GolferDeleteDialogComponent,
  golferRoute,
  golferPopupRoute
} from './';

const ENTITY_STATES = [...golferRoute, ...golferPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [GolferComponent, GolferDetailComponent, GolferUpdateComponent, GolferDeleteDialogComponent, GolferDeletePopupComponent],
  entryComponents: [GolferComponent, GolferUpdateComponent, GolferDeleteDialogComponent, GolferDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationGolferModule {}
