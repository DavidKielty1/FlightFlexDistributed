import { Outlet } from "react-router";

export default function Layout() {
  return (
    <div className="main-body">
      <nav>{/* Add navigation if needed */}</nav>
      <main>
        <Outlet />
      </main>
    </div>
  );
}
