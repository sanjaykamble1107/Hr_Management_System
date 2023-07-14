export class Title {
    title: string;
    fromDate: Date;
    toDate: Date;
  
    constructor(data: any) {
      this.title = data.title;
      this.fromDate = new Date(data.fromDate);
      this.toDate = new Date(data.toDate);
    }
  }