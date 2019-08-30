import { NgModule } from '@angular/core';

import { GolfApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [GolfApplicationSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [GolfApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class GolfApplicationSharedCommonModule {}
