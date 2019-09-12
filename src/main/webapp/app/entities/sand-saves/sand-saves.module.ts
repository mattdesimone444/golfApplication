import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SandSavesComponent,
  SandSavesDetailComponent,
  SandSavesUpdateComponent,
  SandSavesDeletePopupComponent,
  SandSavesDeleteDialogComponent,
  sandSavesRoute,
  sandSavesPopupRoute
} from './';

const ENTITY_STATES = [...sandSavesRoute, ...sandSavesPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SandSavesComponent,
    SandSavesDetailComponent,
    SandSavesUpdateComponent,
    SandSavesDeleteDialogComponent,
    SandSavesDeletePopupComponent
  ],
  entryComponents: [SandSavesComponent, SandSavesUpdateComponent, SandSavesDeleteDialogComponent, SandSavesDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSandSavesModule {}
