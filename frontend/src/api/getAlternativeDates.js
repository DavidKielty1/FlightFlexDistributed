import axios from "axios";

const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
console.log("BASE_URL being used:", BASE_URL);

export async function getAlternativeDate(userId) {
  try {
    const response = await axios.get(`${BASE_URL}/alternative/date`, {
      params: { userId },
    });
    return response.data;
  } catch (error) {
    console.error(
      "Error fetching alternative date:",
      error.response || error.message
    );
    throw error;
  }
}
