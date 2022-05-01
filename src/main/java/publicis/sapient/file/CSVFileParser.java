package publicis.sapient.file;

import au.com.bytecode.opencsv.CSVReader;
import publicis.sapient.mower.model.line.Line;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an example of file parser.
 * Implementation of FileParser
 * Handling CSV file type.
 * It uses the openCSV api.
 * Reads the file line by line.
 *
 */
@Component
@Scope("prototype")
public abstract class CSVFileParser implements FileParser {

    protected Logger logger = Logger.getLogger(CSVFileParser.class.getSimpleName());

    protected static final Character LINE_ITEM_SEPARATOR = ';';

    /**
     *
     * @param csvReader
     * @return the next line
     * @throws IOException
     */
    @Override
    public final Line nextLines(CSVReader csvReader, boolean isHeader) throws IOException {
        List<String[]> lines = new ArrayList<>();
        int itemToRead = getLinePerItem(isHeader);
        for (int i = 0; i < itemToRead; i++) {
            String[] strings;
            try {
                strings = (csvReader != null) ? csvReader.readNext() : null;
                if (strings == null && i == 0) {
                    //file ends and no more mowers.
                    break;
                } else if (strings == null && i > 0 && i < itemToRead - 1) {
                    throw new IllegalStateException("There is a problem with the file format. Expected "
                            + itemToRead + " but did found only " + (i));
                } else {
                    lines.add(strings);
                }
            } catch (IOException e) {
                if(logger.isLoggable(Level.SEVERE)) {
                    logger.log(Level.SEVERE, String.format("In out exception while file parsing : %s" , e.getMessage()));
                }
                throw e;
            }
        }
        return !lines.isEmpty() ? newItem(lines, isHeader) : null;
    }

    private int getLinePerItem(boolean isHeader) {
        return isHeader ? getHeaderLinesCount() : getLinePerItem();
    }


    public Character getLineItemSeparator() {
        return LINE_ITEM_SEPARATOR;
    }
}
