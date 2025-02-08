import axios from "axios";
import { handleApiError } from "../utils/apiError";

const BASE_URL = import.meta.env.VITE_BASE_URL || "http://localhost:8080";

export async function getAdRecommendations(userId: string): Promise<string[]> {
  console.log("🔍 API Call - getAdRecommendations", { userId });
  
  try {
    console.log("📤 Making request to:", `${BASE_URL}/ads/recommendations`);
    const response = await axios.get(`${BASE_URL}/ads/recommendations`, {
      params: { userId },
    });
    console.log("📥 API response:", response.data);
    return response.data;
  } catch (error) {
    console.error("💥 API Error:", error);
    handleApiError(error);
  }
}
