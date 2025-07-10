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

# Step 2: GET with Authorization header
echo "📡 Requesting /api/pp-under-care..."

RESPONSE=$(curl -s -X GET https://tinova.uk/api/pp-under-care \
  -H "Authorization: Bearer $JWT" \
  -H "Accept: application/json")

echo "$RESPONSE"

