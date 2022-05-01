package publicis.sapient.file;

import publicis.sapient.mower.model.line.Header;
import publicis.sapient.mower.model.line.Line;

/**
 * Parent class to every file processor.
 */
public abstract class AbstractFileProcessor <T> implements FileProcessor{
    /**
     * the file name pattern should be handled by the processor.
     */
    private String filePattern;
    /**
     * the corresponding file parser.
     */
    private FileParser fileParser;

    public abstract T process(Header header, Line line);

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }


}
