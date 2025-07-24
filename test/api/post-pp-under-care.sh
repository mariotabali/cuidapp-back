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

RESPONSE=$(curl -X POST "$SERVER_PREFIX/api/pp-under-care" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $JWT" \
  -H "Accept:application/json" \
  -d '{
    "name": "Jane Doe",
    "dob": "1990-04-15",
    "gender": "female",
    "address": "123 Elm Street",
    "carer-id": 2
}')
echo $RESPONSE


