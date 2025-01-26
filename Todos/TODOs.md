Precompute and cache generic or frequently used data, like ads or common flight paths.
Process user-specific queries dynamically, leveraging precomputed data where possible.
Use caching mechanisms (e.g., Redis) for high-demand data to improve query performance without excessive recomputation.

On-Demand
Dynamic, user-specific processing is needed for:

Live data: Real-time flight availability, pricing, and custom recommendations.
Highly interactive scenarios: When user input drastically narrows the data scope (e.g., searching for flights from SFO to HKG on a specific date under $500).
