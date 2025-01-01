import axios from "axios";

const BASE_URL = "http://localhost:8081"; // Replace with your backend's REST gateway URL

// Fetch ad recommendations
export async function getAdRecommendations(userId) {
  try {
    const response = await axios.get(`${BASE_URL}/ads/recommendations`, {
      params: { userId },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching ad recommendations:", error);
    throw error;
  }
}
