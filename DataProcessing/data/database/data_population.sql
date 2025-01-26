-- Populate precomputed_routes with 300 rows
DO
$$
BEGIN
    FOR i IN 1..300 LOOP
        INSERT INTO precomputed_routes (flight_id, origin, destination, average_price, availability, top_airlines)
        VALUES (
            'FL' || LPAD(i::TEXT, 4, '0'),
            (ARRAY['ATL', 'SFO', 'LAX', 'NYC', 'CHI'])[FLOOR(random() * 5 + 1)::INT],
            (ARRAY['AMS', 'HKG', 'LON', 'PAR', 'BER'])[FLOOR(random() * 5 + 1)::INT],
            300 + FLOOR(random() * 700),
            (ARRAY['Low', 'Medium', 'High'])[FLOOR(random() * 3 + 1)::INT],
            ARRAY[
                (ARRAY['KLM', 'Delta', 'Cathay Pacific', 'United', 'Lufthansa', 'Air France', 'British Airways', 'Virgin Atlantic'])[FLOOR(random() * 8 + 1)::INT],
                (ARRAY['KLM', 'Delta', 'Cathay Pacific', 'United', 'Lufthansa', 'Air France', 'British Airways', 'Virgin Atlantic'])[FLOOR(random() * 8 + 1)::INT]
            ]::TEXT
        );
    END LOOP;
END;
$$;

-- Populate alternative_dates with 300 rows
DO
$$
BEGIN
    FOR i IN 1..300 LOOP
        INSERT INTO alternative_dates (flight_id, suggested_date, origin, destination, price, availability)
        VALUES (
            'FL' || LPAD(i::TEXT, 4, '0'),
            CURRENT_DATE + FLOOR(random() * 30)::INT,
            (ARRAY['ATL', 'SFO', 'LAX', 'NYC', 'CHI'])[FLOOR(random() * 5 + 1)::INT],
            (ARRAY['AMS', 'HKG', 'LON', 'PAR', 'BER'])[FLOOR(random() * 5 + 1)::INT],
            200 + FLOOR(random() * 500),
            (ARRAY['Low', 'Medium', 'High'])[FLOOR(random() * 3 + 1)::INT]
        );
    END LOOP;
END;
$$;

-- Populate user_segments with 300 rows
DO
$$
BEGIN
    FOR i IN 1..300 LOOP
        INSERT INTO user_segments (user_id, prediction)
        VALUES (
            i, -- user_id
            FLOOR(random() * 3)::INT -- prediction (0, 1, or 2)
        );
    END LOOP;
END;
$$;

-- Populate ad_recommendations with 300 rows
DO
$$
BEGIN
    FOR i IN 1..300 LOOP
        INSERT INTO ad_recommendations (user_id, recommended_ads)
        VALUES (
            i, -- user_id
            CASE FLOOR(random() * 3)::INT
                WHEN 0 THEN 'Budget-Friendly Vacation Packages, Luxury Hotel Deals, Special Discounts on Flights!'
                WHEN 1 THEN 'Exclusive Business Class Offers, Budget-Friendly Vacation Packages, Luxury Hotel Deals!'
                WHEN 2 THEN 'Luxury Hotel Deals, Budget-Friendly Vacation Packages, Special Discounts on Flights!'
            END -- recommended_ads
        );
    END LOOP;
END;
$$;
