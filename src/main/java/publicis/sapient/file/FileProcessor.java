package publicis.sapient.file;


public interface FileProcessor {
    String getFilePattern();

    FileParser getFileParser();
}
