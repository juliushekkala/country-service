#Country Service Coding Exercise

This directory contains the source code for the Country service coding exercise.

##How to run the code

Run the server (in the project root):

```console
mvnw spring-boot:run
```

For the client, open another console.
Get all the countries:

```console
curl localhost:8080/countries/
```

Get a single country:
```console
curl localhost:8080/countries/{name}
```

Browser can be used of course as well.

Run the tests (in the project root):
```console
mvnw test
```

##Notes

The single country endpoint can be queried with incomplete country names (e.g. "nl"), it will always 
return one result country but if the query is just a few characters, the result might not be what you expect.
If no country is found, it will return 404.

All test are located in the CountryServiceApplicationTests.java file






###My setup just in case
Used Java version: Java 11

JDK version: 1.8.0_282

