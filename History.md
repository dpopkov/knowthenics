# History

#### 2021-01-26
* Fix: When edit Answer after failed validation page displays Answer as 'New'. Closes #49.
* Fix: Pages 'Find Questions', 'Question Details' do not highlight appropriate menu item. Closes #50.
* Add textarea for big fields on 'Add Question' page.

#### 2021-01-25
* Refactor (Divide) Controllers that have groups of different URLs. Closes #48.

#### 2021-01-24
* Add validation to Answer and AnswerController. Closes #46.
* Add validation to Category, KeyTerm, and Source. Closes #47.

#### 2021-01-23
* Add validation to Question and QuestionController.

#### 2021-01-22
* Add Exception with ResponseStatus annotation and use it in Controllers. Closes #43.
* Add Exception Handler and error pages. Closes #44.
* Add global ControllerExceptionHandler. Closes #45.

#### 2021-01-20
* Add ability to add or update Answer. Closes #41.
* Fix updating Question. Closes #42.

#### 2021-01-19
* Add ability to add or update Source. Closes #39.
* Add ability to add or update KeyTerm. Closes #40.

#### 2021-01-18
* Add ability to add or update Category. Closes #38.

#### 2021-01-17
* Add ability to add or update a Question. Closes #37.

#### 2021-01-16
* Display Question Record. Closes #34.
* Add in the Question Details page link to edit Answer, link to view Answer Details page. Closes #35.
* Implement Find Question. Closes #36.

#### 2021-01-13
* Create DTO Objects and Converters. Closes #33.

#### 2021-01-01
* Write CRUD tests for Map Services. Closes #30.
* Write CRUD tests for Spring Data JPA Services. Closes #31.
* Write Spring MVC tests for Controllers. Closes #32.

#### 2020-12-31
* Add Project Lombok and Refactor. Closes #28.
* Setup Builder Pattern for Entities using Lombok. Closes #29.

#### 2020-12-30
* Add the config.yml for Circle CI.
* Add CircleCI Build Badge #27. Should close #27.

#### 2020-12-24
* Convert Entities to JPA Entities. Closes #22.
* Add Spring Data JPA Repositories. Closes #23.
* Create Spring Data JPA Services for main Entities. Closes #24.
* Load more Bootstrap Data (Answers, Sources, KeyTerms). Closes #25.
* Update Profiles to use Spring Data JPA Services. Closes #26.

#### 2020-12-18
* Apply master layout to Index Page. Closes #19.
* Add missing i18n properties files. Closes #18.
* Apply master layout to main Entities List pages. Closes #20.
* Fix the Menu and Links. Closes #21.

#### 2020-12-16
* Copy static resources and Master template. Closes #16.
* Add WRO4J Maven Plugin and webjars. Closes #17.

#### 2020-12-13
* Add KnowThenics Index Page and Controller. Closes #9.
* Created Index pages and Controllers for Main Entities. Closes #10.
* Loaded Bootstrap Data (partially) on Startup. Closes #11.
* Implemented (partially) Spring Config for Services. Closes #14.
* List all Entities on every Index page for main Entities (Category, Question, Answer, Source). Closes #12.
* Update Services Map Impl to manage setting of ID Property. Closes #15.

#### 2020-12-11
* Created Interfaces for KnowThenics Services. Closes #4.
* Create BaseEntity to add ID to model objects. Closes #5.
* Refactored Service Interfaces to Common Base Interface. Closes #6.
* Implemented Map Based Services. Closes #7.

#### 2020-12-09
* Created multi-module project. Closes #1.
* Created POJO Data Model. Closes #2.
* Added maven-release-plugin. Closes #3.
