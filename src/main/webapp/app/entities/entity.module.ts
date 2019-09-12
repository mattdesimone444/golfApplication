import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'golfer',
        loadChildren: () => import('./golfer/golfer.module').then(m => m.GolfApplicationGolferModule)
      },
      {
        path: 'tournament',
        loadChildren: () => import('./tournament/tournament.module').then(m => m.GolfApplicationTournamentModule)
      },
      {
        path: 'scores',
        loadChildren: () => import('./scores/scores.module').then(m => m.GolfApplicationScoresModule)
      },
      {
        path: 'sg-off-the-tee',
        loadChildren: () => import('./sg-off-the-tee/sg-off-the-tee.module').then(m => m.GolfApplicationSGOffTheTeeModule)
      },
      {
        path: 'tournament-putting-analysis',
        loadChildren: () =>
          import('./tournament-putting-analysis/tournament-putting-analysis.module').then(
            m => m.GolfApplicationTournamentPuttingAnalysisModule
          )
      },
      {
        path: 'putting-analysis',
        loadChildren: () => import('./putting-analysis/putting-analysis.module').then(m => m.GolfApplicationPuttingAnalysisModule)
      },
      {
        path: 'sg-total',
        loadChildren: () => import('./sg-total/sg-total.module').then(m => m.GolfApplicationSGTotalModule)
      },
      {
        path: 'sg-tee-to-green',
        loadChildren: () => import('./sg-tee-to-green/sg-tee-to-green.module').then(m => m.GolfApplicationSGTeeToGreenModule)
      },
      {
        path: 'sg-putting',
        loadChildren: () => import('./sg-putting/sg-putting.module').then(m => m.GolfApplicationSGPuttingModule)
      },
      {
        path: 'sg-arround-the-green',
        loadChildren: () => import('./sg-arround-the-green/sg-arround-the-green.module').then(m => m.GolfApplicationSGArroundTheGreenModule)
      },
      {
        path: 'sg-approach',
        loadChildren: () => import('./sg-approach/sg-approach.module').then(m => m.GolfApplicationSGApproachModule)
      },
      {
        path: 'putts-made-distance',
        loadChildren: () => import('./putts-made-distance/putts-made-distance.module').then(m => m.GolfApplicationPuttsMadeDistanceModule)
      },
      {
        path: 'approach-shot-distance-to-pin',
        loadChildren: () =>
          import('./approach-shot-distance-to-pin/approach-shot-distance-to-pin.module').then(
            m => m.GolfApplicationApproachShotDistanceToPinModule
          )
      },
      {
        path: 'distance-of-approach',
        loadChildren: () =>
          import('./distance-of-approach/distance-of-approach.module').then(m => m.GolfApplicationDistanceOfApproachModule)
      },
      {
        path: 'driving-distance',
        loadChildren: () => import('./driving-distance/driving-distance.module').then(m => m.GolfApplicationDrivingDistanceModule)
      },
      {
        path: 'putts',
        loadChildren: () => import('./putts/putts.module').then(m => m.GolfApplicationPuttsModule)
      },
      {
        path: 'sand-saves',
        loadChildren: () => import('./sand-saves/sand-saves.module').then(m => m.GolfApplicationSandSavesModule)
      },
      {
        path: 'greens-in-regulation',
        loadChildren: () =>
          import('./greens-in-regulation/greens-in-regulation.module').then(m => m.GolfApplicationGreensInRegulationModule)
      },
      {
        path: 'fairways-hit',
        loadChildren: () => import('./fairways-hit/fairways-hit.module').then(m => m.GolfApplicationFairwaysHitModule)
      },
      {
        path: 'course',
        loadChildren: () => import('./course/course.module').then(m => m.GolfApplicationCourseModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationEntityModule {}
