import { Aircraft } from "./aircraft";

export interface VirtualAirline {
    airlineName:string;
    airline:string;
    flight_number:number;
    isRepetitive:boolean;
    international:number;
    baseCountry:string;
    hubs:string[];
    routeDentisy:number;
    extremeDemand:string[];
    bigDemand:string[];
    mediumDemand:string[];
    aircraft:Aircraft[];
}
    
