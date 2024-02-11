# Recipe API Spec

## Display Recipe

Endpoint : GET /api/recipe-display

Request Header :

- X-API-TOKEN : Token (Mandatory)


Response Body (Success) :

```json
[
  {
    "recipe_id"  : 1,
    "image"       : "xxx",
    "name"        : "Sepageti"
  },
  {
    "recipe_id"  : 2,
    "image"       : "xxx",
    "name"        : "Hamburger"
  },
  {
    "recipe_id"  : 3,
    "image"       : "xxx",
    "name"        : "Telor Asin"
  }
]
```

## Detail Recipe

Endpoint : POST /api/recipe-detail

Request Header :

- X-API-TOKEN : Token (Mandatory)


Request Body (Success) :

```json
{
  "recipe_id"   : 1
}
```

Response Body
```json
{
  "recipe_id"   : 1,
  "name"        : "Hamburger",
  "description" : "deskripsi",
  "ingredients" : ["Tomat", "Chery", "Bawang"],
  "favourite"   : true // true or false 
}
```

## Watch Recipe
Endpoint : POST /api/recipe-watch

Request Header :

- X-API-TOKEN : Token (Mandatory)


Request Body (Success) :

```json
{
  "recipe_id"   : 1
}
```

Response Body
```json
{
  "recipe_id"   : 1,
  "name"        : "Hamburger",
  "image"       : "xxx",
  "description" : "deskripsi",
  "steps"       : "......"
}
```