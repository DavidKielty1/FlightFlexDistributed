import React, { useState } from "react";
import { getAdRecommendations } from "../api/getAdRecommendations";

const AdRecommendations: React.FC = () => {
  const [userId, setUserId] = useState<string>("");
  const [recommendations, setRecommendations] = useState<string[]>([]);
  const [error, setError] = useState<string>("");

  const fetchRecommendations = async () => {
    try {
      setError("");
      const ads = await getAdRecommendations(userId);
      setRecommendations(ads);
    } catch (err) {
      setError("Failed to fetch recommendations. Please try again.");
    }
  };

  return (
    <div className="main-body">
      <h1>Ad Recommendations</h1>
      <input
        type="text"
        placeholder="Enter User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
      />
      <button onClick={fetchRecommendations}>Get Recommendations</button>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <ul>
        {recommendations.map((ad, index) => (
          <li key={index}>{ad}</li>
        ))}
      </ul>
    </div>
  );
};

export default AdRecommendations;
