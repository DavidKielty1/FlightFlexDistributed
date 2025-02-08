import { Links, Meta, Outlet, Scripts } from "react-router";
import "./app.css";

export default function Root() {
  return (
    <html lang="en" suppressHydrationWarning>
      <head>
        <meta charSet="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <Meta />
        <Links />
      </head>
      <body suppressHydrationWarning>
        <div id="root">
          <Outlet />
        </div>
        <Scripts />
      </body>
    </html>
  );
}
