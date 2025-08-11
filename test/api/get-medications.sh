#!/bin/bash

CSP_ID="1"  # change this to the actual cared_person_id you want to test

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

# Step 2: GET /api/medications/:cared_person_id
echo "📡 Requesting /api/medications/$CSP_ID..."

RESPONSE=$(curl -s -X GET "https://tinova.uk/api/medications/$CSP_ID" \
  -H "Authorization: Bearer $JWT" \
  -H "Accept: application/json")

echo "$RESPONSE"
