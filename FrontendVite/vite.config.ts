import { reactRouter } from "@react-router/dev/vite";
import autoprefixer from "autoprefixer";
import tailwindcss from "tailwindcss";
import { defineConfig } from "vite";
import tsconfigPaths from "vite-tsconfig-paths";

export default defineConfig({
  css: {
    postcss: {
      plugins: [tailwindcss, autoprefixer],
    },
  },
  plugins: [reactRouter(), tsconfigPaths()],
  resolve: {
    alias: {
      "@": "/src", // Optional alias for the src folder
    },
  },
  server: {
    port: 3000,
    open: true, // Automatically open the app in the browser
  },
  build: {
    outDir: "dist",
    emptyOutDir: true,
  },
});
