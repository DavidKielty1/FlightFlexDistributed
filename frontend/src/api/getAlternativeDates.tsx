import axios from "axios";
import { handleApiError } from "../utils/apiError";

const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";

export interface FlightData {
  origin: string;
  destination: string;
  price: string;
  date: string;
}

export interface AlternativeDate {
  date: string;
  price: string;
  availability: string;
}

export async function getAlternativeDatesByFlight(
  flightData: FlightData
): Promise<AlternativeDate[]> {
  try {
    const response = await axios.get(`${BASE_URL}/alternative/date`, {
      params: flightData,
    });
    return response.data;
  } catch (error) {
    handleApiError(error);
  }
}
