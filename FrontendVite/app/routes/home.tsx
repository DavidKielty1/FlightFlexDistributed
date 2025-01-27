import AdRecommendations from "../components/getAds";
import SearchAlternativeDates from "../components/SearchAlternativeDates";

export default function Home() {
  return (
    <div className="main-body">
      <AdRecommendations />
      <SearchAlternativeDates />
    </div>
  );
}
