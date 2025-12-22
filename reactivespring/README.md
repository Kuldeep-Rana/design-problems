## This app contains three services MovieInfo and MovieService is written in tradtional SPring Rest, but ReviewRouter is written in functional way using the router and handler. 
* [MovieInfoController.java](src%2Fmain%2Fjava%2Fcom%2Freactivespring%2Fcontroller%2FMovieInfoController.java)
* [ReviewRouter.java](src%2Fmain%2Fjava%2Fcom%2Freactivespring%2Ffunctional%2Frouter%2FReviewRouter.java)
* [MovieServiceController.java](src%2Fmain%2Fjava%2Fcom%2Freactivespring%2Fcontroller%2FMovieServiceController.java)


## Sample mongo data MovieInfo
```
{
    "_id" : "M2501",
    "name" : "Neon Dreams 0",
    "year" : NumberInt(2020),
    "cast" : [
        "Rhea Nair",
        "Aditya Roy",
        "Lakshya Kapoor"
    ],
    "releaseDate" : ISODate("2020-01-13T18:30:00.000+0000"),
    "_class" : "com.reactivespring.entity.MovieInfo"
}
```
## Sample mongo data MovieReview
```
{
    "_id" : "R2501",
    "movieInfoId" : "M2501",
    "comment" : "Good one to end the year",
    "rating" : NumberInt(3),
    "_class" : "com.reactivespring.entity.MovieReview"
}
{
    "_id" : "R2502",
    "movieInfoId" : "M2501",
    "comment" : "Good one ",
    "rating" : NumberInt(4),
    "_class" : "com.reactivespring.entity.MovieReview"
}

```



### Here are sample request to test the services.
* create MovieInfo

```
curl --location 'localhost:8080/api/v1/movieinfos' \
--header 'Content-Type: application/json' \
--data '{
  "movieInfoId": "M2501",
  "name": "Neon Dreams 0",
  "year": 2020,
  "cast": ["Rhea Nair", "Aditya Roy", "Lakshya Kapoor"],
  "releaseDate": "2020-01-14"
}'
```

* Get movie Info
```
curl --location 'localhost:8080/api/v1/movieinfos/M2501'
```
* Create Movie review
```
curl --location 'localhost:8080/api/reviews' \
--header 'Content-Type: application/json' \
--data '{
    "reviewId" : "R2502",
    "movieInfoId": "M2501",
    "comment":"Good one ",
    "rating":4

}'
```
* Get all movie reviews
```
curl --location 'localhost:8080/api/reviews'
```

* Get Movie combining info and review
```
curl --location 'localhost:8080/api/movie/M2501'
```