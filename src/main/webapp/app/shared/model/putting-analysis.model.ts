export interface IPuttingAnalysis {
  id?: number;
  puttinAnalysisId?: number;
  longest?: string;
  total?: string;
  lessThree?: string;
  lessTen?: string;
  threeToFive?: string;
  fiveToSeven?: string;
  sevenToTen?: string;
  fourToEight?: string;
  tenToFifteen?: string;
  fifteenToTwenty?: string;
  twentyToTwentyFive?: string;
  lessTwentyFive?: string;
}

export class PuttingAnalysis implements IPuttingAnalysis {
  constructor(
    public id?: number,
    public puttinAnalysisId?: number,
    public longest?: string,
    public total?: string,
    public lessThree?: string,
    public lessTen?: string,
    public threeToFive?: string,
    public fiveToSeven?: string,
    public sevenToTen?: string,
    public fourToEight?: string,
    public tenToFifteen?: string,
    public fifteenToTwenty?: string,
    public twentyToTwentyFive?: string,
    public lessTwentyFive?: string
  ) {}
}
