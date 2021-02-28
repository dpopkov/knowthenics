# History

#### 2021-02-28
* Add links to Home page.
* Remove answers from list of Questions.
* Display text values containing valid url as clickable links. Closes #73.

#### 2021-02-26
* Do not show empty fields on view 'Question Details'. Closes #71.

#### 2021-02-25
* Add new type of Source - Person.
* Remove columns of Answer on Question Details page.
* Add Sources Details page.

#### 2021-02-23
* Implement search of Questions and Answers both by wordingEn and wordingRu fields.

#### 2021-02-20
* Add ability for Question and Answer to add KeyTerms. Closes #54.
* Use white-space pre-wrap style for big fields.

#### 2021-02-18
* Add 'prod' profile to services.
* Fix the problem of writing Russian characters to MySQL. Closes #67.

#### 2021-02-15
* Configure MySQL: setup databases and accounts for dev and prod. Closes #55.
* Add Spring Boot configuration for MySQL. Closes #52.
* Fix error when choosing Question without preferred Answer. Closes #65.
* Fix failing KnowthenicsApplicationTests on CircleCI. Closes #66. 

#### 2021-02-13
* Add ability to drill cards in Deck. Closes #63.

#### 2021-02-05
* Add FlashCard and Deck Entities. Closes #59.
* Add Controllers and View for Deck and FlashCards. Closes #60.

#### 2021-02-04
* Add ability to add and edit Collections of Questions. Closes #57.
* Add Category details, wrap output of big fields in 'pre' element. Add missing links. Closes #58.

#### 2021-01-31
* Implement Combined Search of Questions and Answers. Closes #56.
* Add ability for Question to choose the preferred Answer. Closes #53.

#### 2021-01-29
* Add JaCoCo code coverage.

#### 2021-01-28
* Add KeyTerm details and ability to find Questions and Answers by KeyTerm. Closes #51.

#### 2021-01-27
* Add links to view details to Answers list page.
* Implement Find Answers.

#### 2021-01-26
* Fix: When edit Answer after failed validation page displays Answer as 'New'. Closes #49.
* Fix: Pages 'Find Questions', 'Question Details' do not highlight appropriate menu item. Closes #50.
* Add textarea for big fields on 'Add Question' page.
* Fix: When edit Question after failed validation page displays Question as 'New'.

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
