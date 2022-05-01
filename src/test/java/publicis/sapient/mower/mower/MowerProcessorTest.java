package publicis.sapient.mower.mower;

import org.junit.Assert;
import publicis.sapient.mower.model.Mower;
import publicis.sapient.mower.model.line.SurfaceLine;
import publicis.sapient.mower.model.line.MowerLine;
import publicis.sapient.mower.file.MowerProcessor;
import publicis.sapient.mower.model.Instruction;
import publicis.sapient.mower.model.Orientation;
import publicis.sapient.mower.movement.service.MovementsExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@ContextConfiguration(locations = {"/file-poller-mower.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MowerProcessorTest {

    @Autowired
    private MowerProcessor processor;

    @Autowired
    private MovementsExecutor movementsExecutor;

    @Test
    public void testProcessor() {
        SurfaceLine header = new SurfaceLine(2, 2);
        MowerLine line = new MowerLine();
        line.setOrientation(Orientation.N);
        line.setMowerX(1);
        line.setMowerY(1);
        line.setInstructions(Arrays.asList(new Instruction[]{Instruction.A}));
        final var mower = processor.process(header, line);
        Assert.assertTrue(mower instanceof Mower);
        Assert.assertEquals(1, mower.getInstructions().length);
    }

}
