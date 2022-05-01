filepoller
#This is a small project representing a simple API in order to develop simple file processor.

#It consists of :

- Implementing the Line interface in order to define the object to encapsulate each line in the parsed file.
- Extending the AbstractFileProcessor class and implementing the process method in order to plug specific treatment for each line while parsing.
- The api presents a specific file parser for the specific CSV file type.
- Extending the CSVFileParser, and implement the newLine method in order to create the specific line object type.
- Configure in the file config.properties, the directory path to be watched.
- Configure the specific processor bean by overriding the filePattern attribute and fileParser attribute.
- Configure the specific file parser bean, which would be referenced from the processor.
#mowerProject

This project permits to program the execution of the mower (mower) via an injected file.

The file format is as follow:

            First line = File HEADER = Surface size
                X Y
                ## X = max X position  Y = max Y position. X et Y is separated by a space.
            Rest of the file lines represent a mower = (mower).
                Mower = 2 lines.
                    First line is initial position X Y Orientation (X and Y and orientation are separated by space)
                        Orientation :
                        N : north
                        S : South
                        E : Est
                        O : Ouest
                    Second line represent a suite of instructions
                        R = Right
                        L = Left
                        F = Forward


This program once runned it creates a thread which listen on events of file injection in a configured folder.
The folder is configured in the file config.properties.

Installation steps:
1- git checkout the sources.
2- open the project with your favourite editor.
3- configure the property "watched.file" in the /resources/config.properties.
4- create the configured "watched.file" in your file system (otherwise the project won't compile)