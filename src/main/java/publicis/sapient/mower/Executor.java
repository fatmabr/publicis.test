package publicis.sapient.mower;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Executor {

    public static void main(String... args) {
         new ClassPathXmlApplicationContext("file-poller-mower.xml");
    }

}
