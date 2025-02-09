import axios from "axios";
import { handleApiError } from "../utils/apiError";

const BASE_URL = import.meta.env.VITE_BASE_URL || "http://localhost:8080";

export async function getAdRecommendations(userId: string): Promise<string[]> {
  try {
    const numericUserId = parseInt(userId, 10);
    if (isNaN(numericUserId)) {
      throw new Error("User ID must be a valid number");
    }

    const response = await axios.get<string[]>(
      `${BASE_URL}/ads/recommendations`,
      {
        params: { userId: numericUserId },
      }
    );

    // Add some debug logging
    console.log("Response data:", response.data);

    // Handle both array and single string responses
    if (Array.isArray(response.data)) {
      return response.data;
    } else if (response.data) {
      return [response.data];
    }

    return [];
  } catch (error) {
    throw handleApiError(error);
  }
}
