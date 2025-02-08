import React, { useState } from "react";
import { getAdRecommendations } from "../api/getAdRecommendations";

const AdRecommendations: React.FC = () => {
  const [userId, setUserId] = useState<string>("");
  const [recommendations, setRecommendations] = useState<string[]>([]);
  const [error, setError] = useState<string>("");

  const fetchRecommendations = async () => {
    console.log("🚀 Starting fetch with userId:", userId);

    try {
      setError("");
      const ads = await getAdRecommendations(userId);
      setRecommendations(ads);
    } catch (err) {
      console.error("❌ Component Error:", err);
      setError("Failed to fetch recommendations. Please try again.");
    }
  };

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg">
      <h1 className="text-2xl font-bold mb-6 text-gray-800 dark:text-white">
        Ad Recommendations
      </h1>

      <div className="flex gap-4 mb-6">
        <input
          type="text"
          placeholder="Enter User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          className="flex-1 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
        />
        <button
          onClick={fetchRecommendations}
          className="px-6 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          Get Recommendations
        </button>
      </div>

      {error && <p className="text-red-500 dark:text-red-400 mb-4">{error}</p>}

      {recommendations.length > 0 && (
        <ul className="space-y-2">
          {recommendations.map((ad, index) => (
            <li
              key={index}
              className="p-4 bg-gray-50 dark:bg-gray-700 rounded-md shadow-sm"
            >
              {ad}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default AdRecommendations;
