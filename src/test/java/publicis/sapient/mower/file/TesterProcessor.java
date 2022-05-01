package publicis.sapient.mower.file;

import publicis.sapient.mower.model.Mower;
import publicis.sapient.mower.model.line.Header;
import publicis.sapient.mower.model.line.Line;
import publicis.sapient.file.AbstractFileProcessor;

public class TesterProcessor extends AbstractFileProcessor {

    @Override
    public Mower process(Header header, Line line) {
        return null;
    }

}
