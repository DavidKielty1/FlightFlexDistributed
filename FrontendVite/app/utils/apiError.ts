import axios from "axios";

export type ApiError = {
  message: string;
  code?: string;
};

export const DEFAULT_ERROR = "Something went wrong. Please try again.";
export const VALIDATION_ERROR = "Please check your input and try again.";
export const NETWORK_ERROR =
  "Unable to connect to server. Please check your connection.";

export function handleApiError(error: unknown): never {
  let errorMessage = DEFAULT_ERROR;

  if (axios.isAxiosError(error)) {
    if (error.response) {
      // Server responded with error
      errorMessage =
        error.response.data?.message ||
        (error.response.status === 400 ? VALIDATION_ERROR : DEFAULT_ERROR);
    } else if (error.request) {
      // Request made but no response
      errorMessage = NETWORK_ERROR;
    }
  }

  throw new Error(errorMessage);
}
