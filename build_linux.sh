#!/bin/bash

JFLEX_LIB=$HOME/programming/tools/jflex-1.6.1/jflex-1.6.1/lib/jflex-1.6.1.jar

if [ -e src/LexicalAnalyzer.java ]
then
   rm src/LexicalAnalyzer.java;
fi
java -jar $JFLEX_LIB src/LexicalAnalyzer.flex
