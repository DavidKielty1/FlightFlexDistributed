import axios from "axios";
import { handleApiError } from "../utils/apiError";
import type { FlightData, AlternativeDate } from "../types/flight";

const BASE_URL = import.meta.env.VITE_BASE_URL || "http://localhost:8080";

export async function getAlternativeDatesByFlight(
  flightData: FlightData
): Promise<AlternativeDate[]> {
  try {
    // Log the exact URL that will be called
    const params = new URLSearchParams({
      origin: flightData.origin,
      destination: flightData.destination,
      price: flightData.price.toString(),
      date: flightData.date.split("T")[0],
    });

    const url = `${BASE_URL}/alternative/date?${params.toString()}`;
    console.log("Full URL:", url);

    const response = await axios.get<AlternativeDate[]>(
      `${BASE_URL}/alternative/date`,
      {
        params: {
          origin: flightData.origin,
          destination: flightData.destination,
          price: flightData.price,
          date: flightData.date.split("T")[0],
        },
      }
    );

    console.log("Response:", response.data);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error("Request failed:", {
        data: error.response?.data,
        status: error.response?.status,
        params: error.config?.params,
        url: error.config?.url,
      });
    }
    throw handleApiError(error);
  }
}
