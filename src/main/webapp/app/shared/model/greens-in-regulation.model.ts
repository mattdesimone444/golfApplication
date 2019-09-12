export interface IGreensInRegulation {
  id?: number;
  playerId?: number;
  tournamentId?: number;
  courseId?: number;
  hole1?: string;
  hole2?: string;
  hole3?: string;
  hole4?: string;
  hole5?: string;
  hole6?: string;
  hole7?: string;
  hole8?: string;
  hole9?: string;
  hole10?: string;
  hole11?: string;
  hole12?: string;
  hole13?: string;
  hole14?: string;
  hole15?: string;
  hole16?: string;
  hole17?: string;
  hole18?: string;
}

export class GreensInRegulation implements IGreensInRegulation {
  constructor(
    public id?: number,
    public playerId?: number,
    public tournamentId?: number,
    public courseId?: number,
    public hole1?: string,
    public hole2?: string,
    public hole3?: string,
    public hole4?: string,
    public hole5?: string,
    public hole6?: string,
    public hole7?: string,
    public hole8?: string,
    public hole9?: string,
    public hole10?: string,
    public hole11?: string,
    public hole12?: string,
    public hole13?: string,
    public hole14?: string,
    public hole15?: string,
    public hole16?: string,
    public hole17?: string,
    public hole18?: string
  ) {}
}
