# Bookmark API Spec

## List Recipe
Endpoint : POST /api/recipe-bookmark

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body
```json
{
  "recipe_id"   : 1,
  "name"        : "Hamburger",
  "image"       : "xxx",
  "favourite"   : true
}
```

# Delete

Request Body
```json
{
  "recipe_id"   : 1
}
```

Response Body

```json
{
  "data" : "OK"
}
```