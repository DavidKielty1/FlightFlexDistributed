import axios from "axios";

export function handleApiError(error: unknown): never {
  if (axios.isAxiosError(error)) {
    console.error("Axios error:", error.response?.data || error.message);
    throw new Error(error.response?.data?.message || "API request failed.");
  } else if (error instanceof Error) {
    console.error("Error:", error.message);
    throw new Error(error.message);
  } else {
    console.error("Unexpected error:", error);
    throw new Error("An unexpected error occurred.");
  }
}
