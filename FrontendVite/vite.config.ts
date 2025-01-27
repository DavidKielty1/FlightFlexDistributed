import { defineConfig } from "vite";
import { reactRouter } from "@react-router/dev/vite";
import tsconfigPaths from "vite-tsconfig-paths";
import autoprefixer from "autoprefixer";
import tailwindcss from "tailwindcss";

export default defineConfig({
  css: {
    postcss: {
      plugins: [tailwindcss, autoprefixer],
    },
  },
  plugins: [reactRouter(), tsconfigPaths()],
  resolve: {
    alias: {
      "@": "/src",
    },
  },
  server: {
    port: 3000,
    host: true,
    // open: true,
    watch: {
      usePolling: true,
      interval: 1000,
    },
    hmr: {
      host: "localhost",
      port: 3000,
      overlay: true,
    },
  },
  build: {
    outDir: "dist",
    emptyOutDir: true,
  },
});
