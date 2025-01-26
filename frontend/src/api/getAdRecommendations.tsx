import axios from "axios";
import { handleApiError } from "../utils/apiError";

const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";

export async function getAdRecommendations(userId: string): Promise<string[]> {
  try {
    const response = await axios.get(`${BASE_URL}/ads/recommendations`, {
      params: { userId },
    });
    return response.data;
  } catch (error) {
    handleApiError(error);
  }
}
