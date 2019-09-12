export interface ITournamentPuttingAnalysis {
  id?: number;
  playerId?: number;
  tournamentId?: number;
  courseId?: number;
  roundOneId?: number;
  roundTwoId?: number;
  roundThreeId?: number;
  roundFourId?: number;
}

export class TournamentPuttingAnalysis implements ITournamentPuttingAnalysis {
  constructor(
    public id?: number,
    public playerId?: number,
    public tournamentId?: number,
    public courseId?: number,
    public roundOneId?: number,
    public roundTwoId?: number,
    public roundThreeId?: number,
    public roundFourId?: number
  ) {}
}
