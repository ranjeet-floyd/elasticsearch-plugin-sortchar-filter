# Elasticsearch Plugin  Sortchar Filter
elasticsearch  plugin filter to sort char. Before indexing sort chars.
## Installation  
Copy build jar in `<path>/<to>/<elasticsearch>/plugins/sortchar-filter` where `<path>/<to>/<elasticsearch>` is the path to your Elasticsearch installation. 

**Restart Elastic search, it can be used as a plugin right away. Check ES logs for any Errors.**

## How to use

### 1. Create on test index with custom analyzer using [sense](https://chrome.google.com/webstore/detail/sense-beta/lhjgkmllcaadmopgmanpapmpjgmfcfig?hl=en)

```markdown
PUT test
{
  "mappings": {
    "terms": {
      "properties": {
        "name": {
          "type": "string",
          "analyzer": "sortchar_custom_analyser"
        }
      }
    }
  },
  "settings": {
    "index": {
      "analysis": {
        "filter": {
          "sort_chars": {
            "sort_order": "asc",
            "type": "sortchar"
          }
        },
        "analyzer": {
          "lowercase_analyzer": {
            "filter": "lowercase",
            "tokenizer": "keyword"
          },
          "sortchar_custom_analyser": {
            "filter": [
              "lowercase",
              "sort_chars"
            ],
            "tokenizer": "whitespace"
          }
        }
      }
    }
  }
}
```

### 2. Test analyzer 
`GET test/_analyze?analyzer=sortchar_custom_analyser&pretty=true&text=bac `
**Result**
`{
   "tokens": [
      {
         "token": "abc",
         "start_offset": 0,
         "end_offset": 0,
         "type": "word",
         "position": 1
      }
   ]
}`

### 3. Index some data
`POST test/names/1
{
   "name": "a and b"
}`


### 4. Validate using Search 
`GET test/_search
{
    "query": {"match": {
       "name": "a and b"
    }}
}`
**OR**
`GET test/_search
{
    "query": {"match": {
       "name": "b and a"
    }}
}`

**Both will produce same result.** 


