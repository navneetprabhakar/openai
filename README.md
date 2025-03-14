# openai
This project creates a service which integrates with the OpenAI API to generate text completions based on user input. It uses Spring Boot for building the service and includes various components such as controllers, services, and helpers to handle requests, prepare payloads, and manage context data. The service supports features like context-aware responses and authentication headers for API calls.


## Sample Context API Payload:
``` Javascript
curl --location 'localhost:8080/openai-demo/v1/context' \
--header 'Content-Type: application/json' \
--data '{
    "name":"first",
    "description":"first demo model",
    "context":["This model is just for trial.","All answers must be given with the prefix: Yes My Overlord ","Any question or statement being passed on to you are in english but you have to answer them in hindi"]
}'

```

## Sample Chat Complete API Payload:

``` Javascript
curl --location 'localhost:8080/openai-demo/v1/chat/complete' \
--header 'Content-Type: application/json' \
--data '{
    "message":"Summarise 2025 ICC Champions Trophy Journey for Team India",
    "model": "gpt-4o-mini",
    "theme":"first"
}'

```
