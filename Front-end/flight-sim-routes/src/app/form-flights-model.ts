export interface FormFlightsModel {
    title: string;
    placeholder: string;
    type: string;
    min?: number;
    max?: number;
    rangeNum?:string;
    formControlName:string;
    required: boolean;
}
