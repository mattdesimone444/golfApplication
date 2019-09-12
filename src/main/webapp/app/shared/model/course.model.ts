export interface ICourse {
  id?: number;
  name?: string;
  par?: number;
  yardage?: number;
  city?: string;
  state?: string;
  courseType?: string;
  greenType?: string;
  hole1Par?: number;
  hole1Yardage?: number;
  hole1Handicap?: number;
  hole2Par?: number;
  hole2Yardage?: number;
  hole2Handicap?: number;
  hole3Par?: number;
  hole3Yardage?: number;
  hole3Handicap?: number;
  hole4Par?: number;
  hole4Yardage?: number;
  hole4Handicap?: number;
  hole5Par?: number;
  hole5Yardage?: number;
  hole5Handicap?: number;
  hole6Par?: number;
  hole6Yardage?: number;
  hole6Handicap?: number;
  hole7Par?: number;
  hole7Yardage?: number;
  hole7Handicap?: number;
  hole8Par?: number;
  hole8Yardage?: number;
  hole8Handicap?: number;
  hole9Par?: number;
  hole9Yardage?: number;
  hole9Handicap?: number;
  hole10Par?: number;
  hole10Yardage?: number;
  hole10Handicap?: number;
  hole11Par?: number;
  hole11Yardage?: number;
  hole11Handicap?: number;
  hole12Par?: number;
  hole12Yardage?: number;
  hole12Handicap?: number;
  hole13Par?: number;
  hole13Yardage?: number;
  hole13Handicap?: number;
  hole14Par?: number;
  hole14Yardage?: number;
  hole14Handicap?: number;
  hole15Par?: number;
  hole15Yardage?: number;
  hole15Handicap?: number;
  hole16Par?: number;
  hole16Yardage?: number;
  hole16Handicap?: number;
  hole17Par?: number;
  hole17Yardage?: number;
  hole17Handicap?: number;
  hole18Par?: number;
  hole18Yardage?: number;
  hole18Handicap?: number;
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public name?: string,
    public par?: number,
    public yardage?: number,
    public city?: string,
    public state?: string,
    public courseType?: string,
    public greenType?: string,
    public hole1Par?: number,
    public hole1Yardage?: number,
    public hole1Handicap?: number,
    public hole2Par?: number,
    public hole2Yardage?: number,
    public hole2Handicap?: number,
    public hole3Par?: number,
    public hole3Yardage?: number,
    public hole3Handicap?: number,
    public hole4Par?: number,
    public hole4Yardage?: number,
    public hole4Handicap?: number,
    public hole5Par?: number,
    public hole5Yardage?: number,
    public hole5Handicap?: number,
    public hole6Par?: number,
    public hole6Yardage?: number,
    public hole6Handicap?: number,
    public hole7Par?: number,
    public hole7Yardage?: number,
    public hole7Handicap?: number,
    public hole8Par?: number,
    public hole8Yardage?: number,
    public hole8Handicap?: number,
    public hole9Par?: number,
    public hole9Yardage?: number,
    public hole9Handicap?: number,
    public hole10Par?: number,
    public hole10Yardage?: number,
    public hole10Handicap?: number,
    public hole11Par?: number,
    public hole11Yardage?: number,
    public hole11Handicap?: number,
    public hole12Par?: number,
    public hole12Yardage?: number,
    public hole12Handicap?: number,
    public hole13Par?: number,
    public hole13Yardage?: number,
    public hole13Handicap?: number,
    public hole14Par?: number,
    public hole14Yardage?: number,
    public hole14Handicap?: number,
    public hole15Par?: number,
    public hole15Yardage?: number,
    public hole15Handicap?: number,
    public hole16Par?: number,
    public hole16Yardage?: number,
    public hole16Handicap?: number,
    public hole17Par?: number,
    public hole17Yardage?: number,
    public hole17Handicap?: number,
    public hole18Par?: number,
    public hole18Yardage?: number,
    public hole18Handicap?: number
  ) {}
}
