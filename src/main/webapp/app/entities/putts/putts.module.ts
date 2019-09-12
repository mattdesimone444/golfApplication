import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  PuttsComponent,
  PuttsDetailComponent,
  PuttsUpdateComponent,
  PuttsDeletePopupComponent,
  PuttsDeleteDialogComponent,
  puttsRoute,
  puttsPopupRoute
} from './';

const ENTITY_STATES = [...puttsRoute, ...puttsPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [PuttsComponent, PuttsDetailComponent, PuttsUpdateComponent, PuttsDeleteDialogComponent, PuttsDeletePopupComponent],
  entryComponents: [PuttsComponent, PuttsUpdateComponent, PuttsDeleteDialogComponent, PuttsDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationPuttsModule {}
