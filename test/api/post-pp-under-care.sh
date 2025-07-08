curl -X POST "http://localhost:3000/api/pp-under-care" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "dob": "1990-04-15",
    "gender": "female",
    "address": "123 Elm Street",
    "carer-id": 1
  }'

