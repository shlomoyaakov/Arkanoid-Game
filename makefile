# yakovsh2

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*/*.java src/Test.java
run:	
	java -cp biuoop-1.4.jar:bin Ass6Game 1 2 3 4

bin:
	mkdir bin
	
jar:
	jar cfm ass7game.jar Manifest.mf -C bin .