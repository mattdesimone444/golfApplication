import { Moment } from 'moment';

export interface ITournament {
  id?: number;
  name?: string;
  season?: number;
  startDate?: Moment;
  endDate?: Moment;
  purse?: number;
  courseId?: number;
  pgaId?: number;
  pgaSeasonId?: number;
  loaded?: boolean;
}

export class Tournament implements ITournament {
  constructor(
    public id?: number,
    public name?: string,
    public season?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public purse?: number,
    public courseId?: number,
    public pgaId?: number,
    public pgaSeasonId?: number,
    public loaded?: boolean
  ) {
    this.loaded = this.loaded || false;
  }
}
