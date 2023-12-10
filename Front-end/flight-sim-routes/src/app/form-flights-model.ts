export interface FormFlightsModel {
    title: string;
    placeholder: string;
    type: string;
    min?: number;
    max?: number;
    rangeNum?:String;
    formControlName:String;
    required: boolean;
}
