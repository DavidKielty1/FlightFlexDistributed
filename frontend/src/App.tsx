import React from "react";
import AdRecommendations from "./components/getAds";
import SearchAlternativeDates from "./components/SearchAlternativeDates";

function App() {
  return (
    <div className="main-body">
      <AdRecommendations />
      <SearchAlternativeDates />
    </div>
  );
}

export default App;
