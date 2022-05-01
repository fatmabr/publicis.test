package publicis.sapient.mower.mower;

import publicis.sapient.file.FileImportHandler;
import publicis.sapient.mower.model.Mower;
import publicis.sapient.mower.model.line.Line;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import publicis.sapient.mower.model.line.MowerLine;
import publicis.sapient.mower.model.Orientation;

import java.io.File;
import java.io.IOException;
import java.util.List;

@ContextConfiguration(locations = {"/file-poller-mower.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FileImportHandlerTest {
    @Autowired
    private FileImportHandler handler;

    @Test
    public void testHandle() {
        final List result = handler.handle(new File(Thread.currentThread().getContextClassLoader().getResource("mower.test.csv").getPath()));
        Assert.assertTrue(result instanceof List);
        Assert.assertTrue(!((List<?>) result).isEmpty());

        final var mower1 = result.get(0);
        Assert.assertTrue(mower1 instanceof Mower);
        Assert.assertEquals(Orientation.N, (((Mower) mower1).getPosition()).getOrientation());
        Assert.assertEquals(1, ((Mower) mower1).getPosition().getX());
        Assert.assertEquals(3, ((Mower) mower1).getPosition().getY());

        final var mower2 = result.get(1);
        Assert.assertTrue(mower2 instanceof Mower);
        Assert.assertEquals(Orientation.E, (((Mower) mower2).getPosition()).getOrientation());
        Assert.assertEquals(5, ((Mower) mower2).getPosition().getX());
        Assert.assertEquals(1, ((Mower) mower2).getPosition().getY());
    }
}
