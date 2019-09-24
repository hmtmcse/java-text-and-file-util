package com.hmtmcse.jtfutil.io.watcher;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {


    public static void watch(String location) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(location);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            Integer j = 0;
            while ((key = watchService.take()) != null) {
                Integer i = 0;
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ". " + i);
                    i++;
                }
                System.out.println("Touhid: " + j);
                j++;
                key.reset();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
