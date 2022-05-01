package publicis.sapient.mower.file;

import publicis.sapient.mower.model.line.Line;
import publicis.sapient.file.CSVFileParser;

import java.util.List;

public class TesterCsvParser extends CSVFileParser {
    @Override
    public Line newItem(List<String[]> line, boolean isHeader) {
        return null;
    }

    @Override
    public int getHeaderLinesCount() {
        return 0;
    }

    @Override
    public int getLinePerItem() {
        return 0;
    }
}
