#!/bin/sh
read -p "Enter predefined words file:" predefinedWords
read -p "Enter the text file :" textFile
read -p "Do you want to save result in CSV format(result.csv)? (Y/N):" saveCSV
printf "Running ... \n\n\n"
java -jar build/WordMatcher-0.0.1-SNAPSHOT.jar $textFile $predefinedWords $saveCSV
