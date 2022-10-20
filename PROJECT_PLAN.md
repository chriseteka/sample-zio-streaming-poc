# Project Plan

## Goal
* Create a simple data pipeline system using ZIO

## What will this system be about
* Input comes in, they are transformed (preprocessed), and then final actions are taken based on the result of the processing

## Sources
For this project, the input are known as sources. They shall come from the following places
* An endpoint which this application exposes
* Observing a directory with, reading data from a text file there
* Listening to kafka events (which for a start will be hosted locally)

## Pipeline
The transformation operation for this project is the pipeline and the core operation of what the project does
* Take input, turn it to a domain object (value understood well within our code)
* Do some computation on the domain object (there will be service classes in the project), like filtering, adding to some list, or whatever.
* Call some naive external endpoint (a small service will be developed). This will help us learn how to make external calls and process their returned values

## Sinks
For this project we assume a sink to be the last things to execute at the end of our streaming. The plan is:
* Store the result to a DB by calling an external service with some payload (this service we shall develop)
* Delete a result from a DB by calling an external service with some payload
* Output results on logs to show the results generated after processing a pipeline
* Store results to a file

## Domain of this project
The project will be around a simple population computing. It aims to do the following:
* When a child is born, their name is sent to the system with the dad or moms BSN, and we can register the child as a citizen
* When they turn an adult, their profiles are recalculated, and they are updated in the population DB as an adult
* When they die, their bio are recalculated, and they are deleted or marked with a different status

## External Service
This isn't a mandatory thing, but since we want to also simulate making external calls in our streaming platform, we will add this. The service will be referred to as LOCAL_HOSPITAL
* They have names of people giving birth in their hospital and some info we will pull
* They can tell us (via API or notification) when events like BIRTH or DEATH occurs
* We'll naively assume them to be the holders of all the PEOPLE in this world, they are the service with access to the DB
* It will be written with a simple language and run locally on my machine

## How to Start
* [ ] Define our external service and the endpoints it has (create as a Spring Boot project and maybe ZIO later)
* [ ] Define the domain classes for the streaming platform
* [ ] Define services for different operations we hope to perform
* [ ] Write the stream and connect them
* [ ] Write tests
* [ ] Run and see