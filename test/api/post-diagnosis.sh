#!/bin/bash

# Step 1: Login and get JWT
LOGIN_RESPONSE=$(curl -s -X POST https://tinova.uk/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"juanperez@gmail.com", "password":"ilikebananas"}')

JWT=$(echo "$LOGIN_RESPONSE" | jq -r '.JWT_TOKEN')

if [[ "$JWT" == "null" || -z "$JWT" ]]; then
  echo "❌ Failed to get JWT"
  echo "$LOGIN_RESPONSE"
  exit 1
fi

echo "✅ Got JWT_TOKEN: $JWT"

# Step 2: POST /api/diagnosis with Authorization header
echo "📡 Sending diagnosis data..."

DIAGNOSIS_PAYLOAD='{
  "cared_person_id": 42,
  "diagnosis_date": "2025-08-05",
  "diagnosis": "Type 2 Diabetes",
  "evolution": "Stable under treatment"
}'

RESPONSE=$(curl -s -X POST https://tinova.uk/api/diagnosis \
  -H "Authorization: Bearer $JWT" \
  -H "Content-Type: application/json" \
  -d "$DIAGNOSIS_PAYLOAD")

echo "📨 Server response:"
echo "$RESPONSE"

