#!/bin/bash
echo "Testing alternative dates API..."
curl -s "http://localhost:8080/alternative/date?origin=NYC&destination=LON&price=500&date=2025-02-10" | json_pp 