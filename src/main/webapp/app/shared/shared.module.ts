import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { GolfApplicationSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [GolfApplicationSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [GolfApplicationSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSharedModule {
  static forRoot() {
    return {
      ngModule: GolfApplicationSharedModule
    };
  }
}
