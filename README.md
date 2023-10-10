# Random IT Question - REST API

## Introduction

This Spring Boot application communicates with quizapi.io to fetch 200 random 
questions from their REST API and stores them in a database along with their 
answers. It provides two endpoints for interacting with the data.

## Endpoints

### 1. GET /api/questions
This endpoint retrieves one random question from the database along with its 
possible answers. The response structure is as follows:

```json
{
  "id": 4,
  "question": "Which of the following is correct about NULL?",
  "answers": [
    {
      "id": 14,
      "answer": "NULL is a special type that only has one value: NULL."
    },
    {
      "id": 15,
      "answer": "The special constant NULL is capitalized by convention, but actually it is case insensitive."
    },
    {
      "id": 16,
      "answer": "NULL is a special type that only has two values: NULL and NOT NULL."
    },
    {
      "id": 17,
      "answer": "The special constant NULL is capitalized by convention and it should be defined as such as it's case sensitive. Meaning null is different than NULL."
    }
  ]
}
```

### 2. POST /api/answers
This endpoint allows you to submit answers to a question by providing the 
question's identifier and the selected answer IDs in the request body. It 
returns whether the provided answers are correct. The request body structure 
is as follows:

```json
{
  "answers": [1, 2],
  "questionId": 1
}
```

Example response:
```json
{
  "correct": true
}
```

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- REST API
- Maven