import type { FlightData } from "../types/flight";

export const validateFlightSearch = (formData: FlightData): string | null => {
  if (!formData.origin || !formData.destination) {
    return "Origin and destination are required";
  }
  if (!formData.price || formData.price <= 0) {
    return "Please enter a valid price";
  }
  if (!formData.date) {
    return "Date is required";
  }

  try {
    // Convert date to YYYY-MM-DD format
    const selectedDate = new Date(formData.date);
    const today = new Date();
    if (selectedDate < today) {
      return "Please select a future date";
    }

    // Validate date format
    if (!/^\d{4}-\d{2}-\d{2}$/.test(formData.date)) {
      return "Invalid date format. Please use YYYY-MM-DD";
    }
  } catch (error) {
    return "Invalid date format";
  }
  return null;
};

export const validateUserId = (userId: string): string | null => {
  if (!userId) {
    return "User ID is required";
  }
  const numericId = parseInt(userId, 10);
  if (isNaN(numericId) || numericId <= 0) {
    return "User ID must be a valid positive number";
  }
  return null;
};
