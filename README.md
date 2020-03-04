The general purpose and objective of this project is to develop a website application with the functionality of returning a detailed list of interesting locations based on Latitude and Longitude values entered by a user. Additionally, the application will support a login feature via a landing page which will allow for existing users to login with their username and password and new users to sign up and have their data stored securely for future use.

***

## Work allocation
- Victoria - Frontend
- Thomas - Frontend
- Mike - Backend (Database/API Endpoints)
- Harry - Backend(Pulltogether)
- Kaijian - Backend(Places API)

## Additional Features
### "Search History" Page
 - Each user can see the locations that they've searched in the past
 - Shown on on the frontend by a 'history' page
 - Implemented on the backend with POST/GET/DELETE endpoints.

#Backend Endpoints

## /auth
### GET

```
"login": {
    "email": "user@example.com",
    "password": "xxxxxx"
	}
```

returns authentication token be sent in subsequent requests as a "Bearer Token" type authentication

### POST
```
"signup": {
    "email": "user@example.com",
    "password": "xxxxxxxxx"
	}
```

Shall return a 400 on complete signup, along with returning authentication token redirect to login page

## /history

### GET
Doesn't need any input, gets user ID from authenticationToken and returns
history for that user.

Returns a 400 and a JSON in the format of the JSON below (200 in the case of no history)

```
"searches": {
  "locationSearch": {
    "id": (a unique number),
    "lat": (regex),
    "long": (regex)
  }, ...
}

```

### POST
Must post a singular locationSearch JSON object

```
"locationSearch": {
  "lat": (regex),
  "long":  (regex)
}
```

lat and long must fit the regex :
>^[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?),\s*[-+]?(180(\.0+)?|((1[0-7]\d)|([1-9]?\d))(\.\d+)?)$

Returns 400 for a successful posting of the specified locationSearch

### DELETE/{$id}
Returns 400 for a successful deletion of the specified ID


### For our boys on mac who want to use intellij

You're going to need a different SDK than just the generic "gcloud-sdk" which installs on the cli. 
You need [this](https://cloud.google.com/appengine/docs/standard/java/download)
Download it and put it somewhere you won't delete it (I suggest /Users/$username/gcloud-sdk/)

Add run configuration in IntelliJ (must be Ultimate)
         
