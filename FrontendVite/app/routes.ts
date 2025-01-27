import { type RouteConfig, route, index, layout } from "@react-router/dev/routes";

export default [
  // Home route (index)
  index("./routes/home.tsx"),

  // Layout route for the main application
  layout("./routes/_layout.tsx", [
    // Ad recommendations route
    route("ads", "./routes/ads.tsx"),

    // Alternative dates route
    route("alternative-dates", "./routes/alternative-dates.tsx"),
  ]),
] satisfies RouteConfig;
