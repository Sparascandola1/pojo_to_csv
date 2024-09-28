# Pojo To CSV Parser

Just a quick example of how to use open csv to parse pojos into csv file with nested beans.

## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes. See deployment
for notes on deploying the project on a live system.

### Prerequisites

Requirements for the software and other tools to build, test and push

- [SpringBoot 3.3.4](https://spring.io/)
- [Java 22](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html)
- [OpenCSV 5.9](https://opencsv.sourceforge.net/)

### Installing

Using the spring dashboard:

    Run the project by clicking run next to the app name in the interface

Using jar:

    mvn package
    java -jar .\target\pojo_to_csv-0.0.1-SNAPSHOT.jar

Once the project is running create a csv of user with this endpoint and payload.

```localhost:8080/writecsv?path=<"Your file path">```

```json
[
    {
        "name": "bob",
        "username": "bob123",
        "contactInformation": [ 
            {
                "email": "bob123@hotmail.com",
                "phoneNumberOne": "1234567890",
                "phoneNumberTwo": ""
            },
            {
                "email": "bob123@gmail.com",
                "phoneNumberOne": "0987654321",
                "phoneNumberTwo": ""
            }
        ],
        "address": {
            "street": "123 ave",
            "city": "nice",
            "state": "WA",
            "zip": "12345"
        }
    },
    {
        "name": "John",
        "username": "john123",
        "contactInformation": [ 
            {
                "email": "john123@hotmail.com",
                "phoneNumberOne": "1234567890",
                "phoneNumberTwo": ""
            },
            {
                "email": "john123@gmail.com",
                "phoneNumberOne": "0987654321",
                "phoneNumberTwo": ""
            }
        ],
        "address": {
            "street": "123 ave",
            "city": "nice",
            "state": "GA",
            "zip": "12345"
        }
    }
]
```

## Running the tests

```mvn test```

## Versioning

We use [Semantic Versioning](http://semver.org/) for versioning. For the versions
available, see the tags on this
repository

## Authors

- **Salvatore Parascandola**
