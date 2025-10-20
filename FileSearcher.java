package File_searcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class FileSearcher {

    private static final List<Path> foundFiles = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {

            // Detect drives
            System.out.println("Detecting drives in your system...");
            File[] drives = File.listRoots();
            if (drives == null || drives.length == 0) {
                System.out.println("No drives found.");
                return;
            }

            System.out.println("Drives detected:");
            for (File drive : drives)
                System.out.println(" - " + drive.getAbsolutePath());

            System.out.print("\nEnter the file/folder name you want to search (with or without extension): ");
            String searchName = input.nextLine().trim();

            if (searchName.isEmpty()) {
                System.out.println("Search name cannot be empty!");
                return;
            }

            ExecutorService executor = Executors.newFixedThreadPool(drives.length);
            long startTime = System.currentTimeMillis();

            for (File drive : drives) {
                executor.submit(() -> {
                    System.out.println("\n>>> Searching in drive: " + drive.getAbsolutePath());
                    searchInDrive(drive.toPath(), searchName);
                });
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            long endTime = System.currentTimeMillis();

            System.out.printf("%nSearch completed in %.2f seconds.%n", (endTime - startTime) / 1000.0);

            if (foundFiles.isEmpty()) {
                System.out.println("\nNo files or folders found containing: " + searchName);
            } else {
                System.out.println("\nFound " + foundFiles.size() + " matching file(s)/folder(s):");
                for (Path path : foundFiles) {
                    System.out.println(path.toAbsolutePath());
                }
            }

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void searchInDrive(Path drive, String searchName) {
        if (drive == null || !Files.exists(drive) || !Files.isReadable(drive)) {
            System.err.println("Cannot access drive: " + drive);
            return;
        }

        try (Stream<Path> stream = Files.walk(drive)) {
            stream.forEach(path -> {
                try {
                    Path fileNamePath = path.getFileName();
                    if (fileNamePath == null) return; // skip drive root (C:\, D:\, etc.)

                    String fname = fileNamePath.toString().toLowerCase();
                    String target = searchName.toLowerCase();

                    if (fname.contains(target)) {
                        foundFiles.add(path);
                        System.out.println("Found: " + path.toAbsolutePath());
                    }

                } catch (Exception e) {
                    // Just log, don't stop search
                    System.err.println("Cannot access: " + path + " (" + e.getMessage() + ")");
                }
            });
        } catch (IOException e) {
            System.err.println("Cannot read drive: " + drive + " (" + e.getMessage() + ")");
        }
    }
}
