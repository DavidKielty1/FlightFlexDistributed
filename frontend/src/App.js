import React from "react";
import AdRecommendations from "./components/getAds";
import AlternativeDate from "./components/getAlternativeDates";

function App() {
  return (
    <div className="main-body">
      <AdRecommendations />
      <AlternativeDate />
    </div>
  );
}

export default App;
