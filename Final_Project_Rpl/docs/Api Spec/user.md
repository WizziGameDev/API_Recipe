# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username"  : "adam",
  "email"     : "adam@gmail.com",
  "password"  : "rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username must not blank, ???"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "adam",
  "password" : "rahasia" 
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token"     : "TOKEN",
    "expiredAt" : 2342342423423 // milliseconds
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Username or password wrong"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "username"  : "adam",
    "email"     : "adam@gmail.com"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "adam", 
  "email"         : "adam@gmail.com", 
  "old_password"  : "new password",
  "new_password"  : "new password"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "username"    : "adam",
    "email"       : "adam@gmail.com"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```