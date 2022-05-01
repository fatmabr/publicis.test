package publicis.sapient.mower.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import publicis.sapient.file.FileProcessor;
import publicis.sapient.file.ProcessorRegistry;
import publicis.sapient.mower.file.TesterCsvParser;
import publicis.sapient.mower.file.TesterProcessor;

@ContextConfiguration("/file-poller-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessorRegistryTest {

    @Autowired
    private ProcessorRegistry processorRegistry;

    @Test
    public void fetchProcessorWithGoodFileNameShouldReturnPProcessor() {
        FileProcessor abstractFileProcessor = processorRegistry.matchProcessor("testxx.csv");
        Assert.assertNotNull(abstractFileProcessor) ;
        Assert.assertTrue(abstractFileProcessor instanceof TesterProcessor);
        Assert.assertTrue(abstractFileProcessor.getFileParser() instanceof TesterCsvParser);
    }


    @Test
    public void fetchProcessorWithBadFileNameShouldReturnNull() {
        FileProcessor abstractFileProcessor = processorRegistry.matchProcessor("xx.csv");
        Assert.assertNull(abstractFileProcessor);
    }

}
