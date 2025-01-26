import React, { useState, ChangeEvent } from "react";
import {
  getAlternativeDatesByFlight,
  FlightData,
  AlternativeDate,
} from "../api/getAlternativeDates";

const SearchAlternativeDates: React.FC = () => {
  const [formData, setFormData] = useState<FlightData>({
    origin: "",
    destination: "",
    price: "",
    date: "",
  });
  const [alternativeDates, setAlternativeDates] = useState<AlternativeDate[]>(
    []
  );

  const [error, setError] = useState<string>("");

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const fetchAlternativeDates = async () => {
    try {
      setError("");
      const dates = await getAlternativeDatesByFlight(formData);
      setAlternativeDates(dates);
    } catch (err) {
      setError("Failed to fetch alternative dates. Please try again.");
    }
  };

  return (
    <div className="main-body">
      <h1>Search Alternative Dates</h1>
      <form>
        <input
          type="text"
          name="origin"
          placeholder="Origin"
          value={formData.origin}
          onChange={handleChange}
        />
        <input
          type="text"
          name="destination"
          placeholder="Destination"
          value={formData.destination}
          onChange={handleChange}
        />
        <input
          type="number"
          name="price"
          placeholder="Price"
          value={formData.price}
          onChange={handleChange}
        />
        <input
          type="date"
          name="date"
          placeholder="Date"
          value={formData.date}
          onChange={handleChange}
        />
        <button type="button" onClick={fetchAlternativeDates}>
          Get Alternative Dates
        </button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {alternativeDates.length > 0 && (
        <div>
          <h2>Alternative Dates</h2>
          <ul>
            {alternativeDates.map((altDate: AlternativeDate, index: number) => (
              <li key={index}>
                <strong>{altDate.date}</strong>: {altDate.price} (
                {altDate.availability})
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default SearchAlternativeDates;
