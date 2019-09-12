import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SGOffTheTeeComponent,
  SGOffTheTeeDetailComponent,
  SGOffTheTeeUpdateComponent,
  SGOffTheTeeDeletePopupComponent,
  SGOffTheTeeDeleteDialogComponent,
  sGOffTheTeeRoute,
  sGOffTheTeePopupRoute
} from './';

const ENTITY_STATES = [...sGOffTheTeeRoute, ...sGOffTheTeePopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SGOffTheTeeComponent,
    SGOffTheTeeDetailComponent,
    SGOffTheTeeUpdateComponent,
    SGOffTheTeeDeleteDialogComponent,
    SGOffTheTeeDeletePopupComponent
  ],
  entryComponents: [SGOffTheTeeComponent, SGOffTheTeeUpdateComponent, SGOffTheTeeDeleteDialogComponent, SGOffTheTeeDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSGOffTheTeeModule {}
