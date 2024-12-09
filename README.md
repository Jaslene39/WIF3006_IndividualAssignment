# WIF 3006 Individual Assignment - Fitness Plan Application

## User
**Add new user**
- POST - http://localhost:8080/api/v1/user/addNewUser
- body : json
    {
        "name": "Penny",
        "email": "penny@gamil.com",
        "age": 36
    }

**Get all user**
- GET - http://localhost:8080/api/v1/user/getAllUsers

**Delete user by userId**
- DELETE - http://localhost:8080/api/v1/user/deleteUser/{userId}}

**Update user by userId**
- PUT - http://localhost:8080/api/v1/user/updateUser/{userId}
- params : (if any)

---

## Fitness Plan
**Add new fitness plan**
- POST - http://localhost:8080/api/v1/user/addNewUser
- body : json
  {
  "user": {
  "id": 7
  },
  "planTitle": "Morning Yoga",
  "planDesc": "A yoga workout",
  "goal": "Calm mind",
  "duration": "4 weeks",
  "difficultyLevel": "Beginner"
  }

**Get all fitness plans**
- GET - http://localhost:8080/api/v1/fitnessPlan/getAllFitnessPlans

**Get fitness plan by user Id**
- GET - http://localhost:8080/api/v1/fitnessPlan/getFitnessPlansByUserId/{userId}

**Delete fitness plan by fitness plan Id**
- DELETE - http://localhost:8080/api/v1/fitnessPlan/deleteFitnessPlan/{fitnessPlanId}

**Update fitness plan by user Id && fitness plan Id**
- PUT - http://localhost:8080/api/v1/fitnessPlan/updateFitnessPlan/{userId}}/{fitnessPlanId}
- params : (if any)

------

## BMI
**Add new BMI data**
- POST - http://localhost:8080/api/v1/bmi/addBmiData
- body : json
    {
    "user": {
    "id": 4
    },
    "height": 169,
    "weight": 50
    }

**Get all BMI data of users**
- GET - http://localhost:8080/api/v1/bmi/getAllBmiData

**Get BMI data by user Id**
- GET - http://localhost:8080/api/v1/bmi/getBmiByUserId/{userId}

**Delete BMI data by bmi Id**
- DELETE - http://localhost:8080/api/v1/bmi/deleteBmiData/{bmiId}

**Update BMI data by user Id**
- PUT - http://localhost:8080/api/v1/bmi/updateBmi/{userId}}
- params : (if any)

