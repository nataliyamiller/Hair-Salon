# _Hair Salon for Everone_

####_by Nataliya Bareeva-Miller_

#####Object-Oriented Java with SQL, Third Week Code Review, Epicodus 28 August 2015.

### Description

_This application helps you manage clients for each stylist. You can also add, delete, or update your favorite stylists. By clicking on a particular stylist you will go to that stylist's personal page, where you can see that stylist's clients and can change each client's information.
Note: after deleting a particular stylist, that stylist's clients will still remain in the SQL database, so we can contact those clients later to see if they want to be re-assigned to another stylist._


## Technologies Used

* _Java version 8 update 45_
* _Velocity version 1.7_
* _Spark version 2.1_
* _Bootstrap version 3.2.0_
* _JUnit version 4.+_
* _FluentLenium version 0.10.3_
* _Gradle_
* _PostgreSQL_
* _Sql2o version 1.5.4_


## Set Up
* _In PSQL:
  _CREATE DATABASE hair_salon;_
  _CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);_
  _CREATE TABLE clients (id serial PRIMARY KEY, client_name varchar, client_phone varchar, stylist_id int);_

* _Clone this repository and run with Gradle (type ``gradle run`` in your command line). Then open localhost:4567 in your browser._


## Tests

_There are both unit and integration tests associated with this application. The test files can be found in the /src/test/java folder.
To run the tests:
_In PSQL: CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon. Type ``gradle test`` in your command line._


## Technologies Used

* _Java version 8 update 45_
* _Velocity version 1.7_
* _Spark version 2.1_
* _Bootstrap version 3.2.0_
* _JUnit version 4.+_
* _FluentLenium version 0.10.3_
* _Gradle_



## Copyright (c) 2015 Copyright Holder All Rights Reserved.
[MIT License 2015] https://github.com/nataliyamiller/basic-template-for-java-projects/blob/master/LICENSE.md
