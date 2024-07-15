
# Overview


WordMatcher is a utility program for finding occurrences of predefined words within a text input. It utilizes a Trie data structure for efficient word lookups.

>**This project requires Java Development Kit (JDK) 8 or later.**

# Building the project

While this project is structured as a Maven project, I have included Bash scripts to simplify building it without Maven. You can compile and run the Java code directly if you prefer.
The build process will generate a single JAR file named `WordMatcher-0.0.1-SNAPSHOT.jar`. This JAR will be executable and includes a manifest with the the main class for running the application.

After building the project, you can find the generated JavaDocs in the `doc` folder within the project's root directory. Open the index.html file in your browser to view the documentation.

**Please make sure the scripts are executable:** 
   `chmod +x run.sh`

#### Option 1: Using Maven

1. Install Maven if you haven't already.
2. Run `mvn clean package` from the project's root directory.

#### Option 2: Using Java and Bash Scripts

1. Ensure you have a JDK(8 or higher) installed.
2. Navigate to the project's root directory.
3. Run the `./build.sh` (Linux/macOS)


#Running the program

### Command-Line Arguments

The program requires the following command-line arguments:

- `args[0]` (mandatory): The path to the input text file.
- `args[1]` (mandatory): The path to the file containing predefined words.
- `args[2]` (optional): A flag to save the results in CSV format(`result.csv`). Set to "y", "yes", or "true" (case-insensitive) to enable.

**Example Usage:**
`java -jar build/WordMatcher-0.0.1-SNAPSHOT.jar input.txt words.txt y`

#### Option 1: Using the ./run.sh script

1. Make sure the script is executable.
2. Run the `./run.sh` (Linux/macOS)
3. Enter information for prompted inputs and press Enter

#### Option 2: Using Java
1. Run  `java -jar build/WordMatcher-0.0.1-SNAPSHOT.jar <text_file> <predefined_words_file> <Yes/No>`

#### Option 3: Using Maven
1. Run `mvn exec:java -Dtext_file=<text_file> -Dpredefined_words_file=<predefined_words_file> -Dsave_csv=<Yes/No>` 

If optional save as CSV flag was ON you can find `result.csv` file the project's root directory.

# Running Tests

Project has 2 tests:
- Unit test for Trie class.
- Integration test for WordMatcher

Integration test uses provided in assessment sample inputs:
- Text:
* Detecting first names is tricky to do even with AI. 
  how do you say a street name is not a first name? *

- And a sample list of predefined words: 
*Name 
Detect 
AI*

To run the integration and unit tests, use the command:

`mvn clean integration-test` or

`mvn clean test` for Unit tests only

