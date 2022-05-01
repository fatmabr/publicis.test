package publicis.sapient.file;

import au.com.bytecode.opencsv.CSVReader;
import publicis.sapient.mower.model.line.Header;
import publicis.sapient.mower.model.line.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FileImportHandler handles the injected files in the watched dir.
 * 1) look for the matching file processor in the processor registry
 */
@Component
public class FileImportHandler {

    Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private ProcessorRegistry registry;

    /**
     * this method handle the input file.
     * 1) Look for the configured File Processor
     * 2) Parse the header
     * 3) Parse the whole lines and apply the process
     *
     * @param file
     * @return the list of object resulting of the file processing.
     */
    public List<Object> handle(File file) {
        var list = new ArrayList<>();
        final FileProcessor fileProcessor = registry.matchProcessor(file.getName());
        if (fileProcessor != null) {
            FileParser fileParser = fileProcessor.getFileParser();
            try (FileInputStream in = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
                 CSVReader csvReader = new CSVReader(inputStreamReader, fileParser.getLineItemSeparator())
            ) {
                Line line;
                Header header = (Header) fileParser.nextLines(csvReader, true);
                while ((line = fileParser.nextLines(csvReader, false)) != null) {
                    final var process = ((AbstractFileProcessor)fileProcessor).process(header, line);
                    list.add(process);
                }
            } catch (IOException e) {
                if (logger.isLoggable(Level.SEVERE)) {
                    logger.severe("There is a problem in the input parsed file format.");
                }
            }
        }
        return list;
    }
}
