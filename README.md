# ClockCli #
Simple commandline application for clocking in/out of work.

## Installation ##
- run `mvn clean install` to generate the executable jar file
- create an alias in `.bash_profile`: `alias clock="java -jar <dirWithJar>/ClockCli-1.0-SNAPSHOT-jar-with-dependencies.jar"`
- run `clock` in a new terminal window to see if it works.

## Usage ##
Simply run `clock` in a new terminal window when you wish to clock in.
Just leave the application running until you can clock out.

By default, these values are set (but may be overridden, run the jar with `--help`to find out how):
- `workDuration`: 8h
- `breakDuration`: 45m