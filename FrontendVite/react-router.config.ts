import type { Config } from "@react-router/dev/config";

export default {
  // Disable SSR to avoid hydration mismatches
  ssr: false,
} satisfies Config;
