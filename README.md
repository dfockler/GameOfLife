Conway's Game of Life Implementation in Java.
===

## How to Run
The program requires Java 1.6 JRE or above to run.
It accepts two command line arguments, filename and generations.
If no generations argument is provided it only prints out the first and second generations

usage: `java GameMain <board file> <# of generations>`

ex: `java GameMain testboard.txt 300`

## Format of Input File
The input file can be any rectangular dimensions, although it doesn't allow for jagged arrays.
It also needs some sort of delimiter between the numbers and only allows for the numbers 0 and 1.

Example Input File:

	0 0 0 0 1
	1 1 1 0 1
	0 0 0 0 1

