package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class ExampleDataBootstrapper {
    public static void bootstrap() throws IOException, URISyntaxException {
        // Checking if we need to bootstrap example data
        var config = Avmeister.instance.getConfig();
        var source = Avmeister.instance.getDataFolder().getPath() + "/parts";

        if (config.getBoolean("_populateWithExampleData")) {
            // Populating with example data
            final URI uri = Avmeister.class.getResource("/parts").toURI();
            FileSystem fileSystem = null;

            if (uri.toString().startsWith("jar:"))
                fileSystem = FileSystems.newFileSystem(uri, Map.of("create", "true"));

            try {
                final Path jarPath = Paths.get(uri);
                final Path target = Path.of(source);

                if (!Files.exists(Path.of(source))) {
                    // Extracting
                    Files.walkFileTree(jarPath, new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
                            Path currentTarget = target.resolve(jarPath.relativize(dir).toString());
                            Files.createDirectories(currentTarget);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                            final Path to = target.resolve(jarPath.relativize(file).toString());
                            Files.copy(file, to, StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }
                    });
                }

                config.set("_populateWithExampleData", null);
                config.save(Avmeister.instance.getDataFolder().getPath() + "/config.yml");
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                if (fileSystem != null) fileSystem.close();
            }
            ;
        }
    }
}
