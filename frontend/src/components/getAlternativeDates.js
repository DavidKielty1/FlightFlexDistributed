import React, { useState } from "react";
import { getAlternativeDate } from "../api/getAlternativeDates";

const AlternativeDate = () => {
  const [userId, setUserId] = useState("");
  const [alternativeDate, setAlternativeDate] = useState("");
  const [error, setError] = useState("");

  const fetchAlternativeDate = async () => {
    try {
      setError("");
      const date = await getAlternativeDate(userId);
      setAlternativeDate(date);
    } catch (err) {
      setError("Failed to fetch alternative date. Please try again.");
    }
  };

  console.log("Rendering AlternativeDate!");

  return (
    <div className="main-body">
      <h1>Alternative Dates</h1>
      <input
        type="text"
        placeholder="Enter User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
      />
      <button onClick={fetchAlternativeDate}>Get Alternative Date</button>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {alternativeDate && (
        <p>
          Alternative Date: <strong>{alternativeDate}</strong>
        </p>
      )}
    </div>
  );
};

export default AlternativeDate;
