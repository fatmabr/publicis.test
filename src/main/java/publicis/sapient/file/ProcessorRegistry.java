package publicis.sapient.file;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processor Registry bu file pattern
 * Every Processor is charged to a file pattern.
 */
@Component
public class ProcessorRegistry implements BeanFactoryAware, InitializingBean {

    Logger logger = Logger.getLogger(ProcessorRegistry.class.getName());

    private ListableBeanFactory beanFactory;

    Map<String, FileProcessor> processorMap;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    public void afterPropertiesSet() throws Exception {
        processorMap = beanFactory.getBeansOfType(FileProcessor.class);
    }

    public FileProcessor matchProcessor(String fileName) {
        for (FileProcessor abstractFileProcessor : processorMap.values()) {
            final Pattern compile = Pattern.compile(abstractFileProcessor.getFilePattern());
            final Matcher matcher = compile.matcher(fileName);
            if (matcher.matches()) {
                return abstractFileProcessor;
            }
        }
        logger.log(Level.SEVERE, ()->String.format("the file %s does not match any configured processor: %s", fileName, processorMap.keySet()));
        return null;
    }
}
