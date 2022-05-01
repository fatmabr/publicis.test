package publicis.sapient.file;

import au.com.bytecode.opencsv.CSVReader;
import publicis.sapient.mower.model.line.Line;

import java.io.IOException;
import java.util.List;

public interface FileParser {

     Character LINE_ITEM_SEPARATOR =';';

     Line newItem(List<String[]> lines, boolean isHeaderLine);

     /**
      *
      * @return the number of lines representing the file Header
      */
     int getHeaderLinesCount();

     /**
      *
      * @return the number of lines representing a business Item
      */
     int getLinePerItem();

     Line nextLines(CSVReader csvReader, boolean isHeader) throws IOException;

     Character getLineItemSeparator();
}
