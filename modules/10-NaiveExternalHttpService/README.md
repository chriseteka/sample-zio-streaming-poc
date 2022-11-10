# 10-NaiveExternalHttpService

## This is a module that we assume to mimic some kind of external service which will be called over http

### This service will:
- [ ] Connect to its own Postgres DB using Quill
- [ ] Serve responses to requests over http
- [ ] Emit Events on some certain conditions/key changes defined by me
- [ ] Contain simple unit tests
- [ ] Contain simple integration tests where test containers shall be used

### Domain Structure (Pertaining to Population)
- Human - has a -> Name, Age, Gender, State
        - we can deduce -> Adulthood, Race
- Family - has a -> familyName, parents, children
         - we can deduce -> FamilyMembers, NumberOfFamilyMembers, NumberOfMinors, NumberOfMajors, etc
- Community - Name, Set of Families
- Country - many Communities put together makes a country
NB: All the aforementioned data shall be persisted, but Country (this is because we have a finite list of countries)

### Actions:
- [ ] Basic CRUD Ops on all 3 (Human, Family, Community) entities
  - Human Ops
    - [ ] For the future
  - Family Ops
    - [ ] For the future
  - Country Ops
    - [ ] For the future
- [ ] Http Endpoints for all 4 (Human, Family, Community, Country)
- [ ] ...we leave here for later when we want to do more stuff like emitting events, and testing
NB: CRUD Ops => CREATE, READ ONE BY ID, READ ALL, UPDATE ONE, DELETE ONE BY ID
