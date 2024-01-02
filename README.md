## Write Your Own wc Tool (Java)
Coding challenge from https://codingchallenges.fyi/challenges/challenge-wc


#### Status

In-progress

#### Requirements

* JDK version 21

#### Build Fat Jar
`$ ./gradlew shadowJar`

#### Run Tests

`$ ./gradlew test`

#### Usage

`$ ./wc [OPTION]... [FILE]...`

When no file is specified, read from stdio.
Linux pipe can be used like

`$ cat [FILE] | ./wc`


