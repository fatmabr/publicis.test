package publicis.sapient.dir.watcher;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import publicis.sapient.file.FileImportHandler;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * WatcherConfigurer configure a watched dir where we can inject the files.
 **/
@Component
public class DirWatcherConfigurer implements InitializingBean {

    Logger logger = Logger.getLogger(getClass().getName());

    @Value("${watched.file}")
    private String watchedDir;

    @Autowired
    private FileImportHandler handler;
    /**
     * This method would configure a watched dir where we can inject the files.
     */
    public void afterPropertiesSet() {
        Path dir = Paths.get(watchedDir);
        WatchService watcher;
        try {
            watcher = FileSystems.getDefault().newWatchService();
            dir.register(watcher,
                    StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Watched dir not configured");
            throw new IllegalStateException("A problem with watched dir configuration : hint : verify the value of the " +
                    "${watched.file} in the file config.properties and assert that it exists in you file system ");
        }
        final FileListener importListener = new FileListener(dir, watcher,handler);
        final Thread thread = new Thread(importListener);
        logger.log(Level.INFO, () -> String.format("WATCHED DIR LOG: %s", watchedDir));
        logger.log(Level.INFO, () -> String.format("WATCHED DIR LOG PRINT: %s", watchedDir));
        thread.start();
    }
}
