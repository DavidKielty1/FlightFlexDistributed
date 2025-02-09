import React, { useState } from "react";
import type { ChangeEvent } from "react";
import type { FlightData, AlternativeDate } from "../types/flight";
import { validateFlightSearch } from "../utils/validation";
import { getAlternativeDatesByFlight } from "../api/getAlternativeDates";
import { DEFAULT_ERROR } from "../utils/apiError";

const SearchAlternativeDates: React.FC = () => {
  const [formData, setFormData] = useState<FlightData>({
    origin: "NYC",
    destination: "LON",
    price: 500,
    date: "2025-02-10",
  });
  const [alternativeDates, setAlternativeDates] = useState<AlternativeDate[]>(
    []
  );
  const [error, setError] = useState<string>("");

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    const finalValue = name === "price" ? Number(value) : value;
    setFormData((prevData) => ({
      ...prevData,
      [name]: finalValue,
    }));
  };

  const fetchAlternativeDates = async () => {
    try {
      const validationError = validateFlightSearch(formData);
      if (validationError) {
        setError(validationError);
        return;
      }

      setError("");
      const dates = await getAlternativeDatesByFlight(formData);
      setAlternativeDates(dates);
    } catch (err) {
      setError(err instanceof Error ? err.message : DEFAULT_ERROR);
    }
  };

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg mt-8">
      <h1 className="text-2xl font-bold mb-6 text-gray-800 dark:text-white">
        Search Alternative Dates
      </h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
        <input
          type="text"
          name="origin"
          placeholder="Origin"
          value={formData.origin}
          onChange={handleChange}
          className="px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
        />
        <input
          type="text"
          name="destination"
          placeholder="Destination"
          value={formData.destination}
          onChange={handleChange}
          className="px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
        />
        <input
          type="number"
          name="price"
          placeholder="Price"
          value={formData.price}
          onChange={handleChange}
          className="px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
        />
        <input
          type="date"
          name="date"
          placeholder="Date"
          value={formData.date}
          onChange={handleChange}
          pattern="\d{4}-\d{2}-\d{2}"
          className="px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
        />
      </div>

      <button
        onClick={fetchAlternativeDates}
        className="w-full px-6 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
      >
        Get Alternative Dates
      </button>

      {error && <p className="text-red-500 dark:text-red-400 mt-4">{error}</p>}

      {alternativeDates.length > 0 && (
        <div className="mt-8">
          <h2 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">
            Alternative Dates
          </h2>
          <div className="space-y-3">
            {alternativeDates.map((altDate: AlternativeDate, index: number) => (
              <div
                key={index}
                className="p-4 bg-gray-50 dark:bg-gray-700 rounded-md shadow-sm flex justify-between items-center"
              >
                <span className="font-medium text-gray-800 dark:text-white">
                  {altDate.suggestedDate}
                </span>
                <span className="text-blue-500 dark:text-blue-400">
                  ${altDate.price}
                </span>
                <span className="text-gray-600 dark:text-gray-300">
                  {altDate.availability}
                </span>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default SearchAlternativeDates;
