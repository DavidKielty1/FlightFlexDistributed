export interface FlightData {
  origin: string;
  destination: string;
  price: number;
  date: string;
}

export interface AlternativeDate {
  flightId: string;
  suggestedDate: string;
  origin: string;
  destination: string;
  price: number;
  availability: string;
}
