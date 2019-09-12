export interface ISGPutting {
  id?: number;
  playerId?: number;
  tournamentId?: number;
  courseId?: number;
  hole1?: number;
  hole2?: number;
  hole3?: number;
  hole4?: number;
  hole5?: number;
  hole6?: number;
  hole7?: number;
  hole8?: number;
  hole9?: number;
  hole10?: number;
  hole11?: number;
  hole12?: number;
  hole13?: number;
  hole14?: number;
  hole15?: number;
  hole16?: number;
  hole17?: number;
  hole18?: number;
}

export class SGPutting implements ISGPutting {
  constructor(
    public id?: number,
    public playerId?: number,
    public tournamentId?: number,
    public courseId?: number,
    public hole1?: number,
    public hole2?: number,
    public hole3?: number,
    public hole4?: number,
    public hole5?: number,
    public hole6?: number,
    public hole7?: number,
    public hole8?: number,
    public hole9?: number,
    public hole10?: number,
    public hole11?: number,
    public hole12?: number,
    public hole13?: number,
    public hole14?: number,
    public hole15?: number,
    public hole16?: number,
    public hole17?: number,
    public hole18?: number
  ) {}
}
