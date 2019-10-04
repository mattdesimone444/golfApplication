export interface IGolfer {
  id?: number;
  name?: string;
  height?: number;
  weight?: number;
  age?: number;
  residenceCity?: string;
  residenceState?: string;
  playsFromCity?: string;
  playsFromState?: string;
  turnedPro?: number;
  pgaId?: number;
}

export class Golfer implements IGolfer {
  constructor(
    public id?: number,
    public name?: string,
    public height?: number,
    public weight?: number,
    public age?: number,
    public residenceCity?: string,
    public residenceState?: string,
    public playsFromCity?: string,
    public playsFromState?: string,
    public turnedPro?: number,
    public pgaId?: number
  ) {}
}
