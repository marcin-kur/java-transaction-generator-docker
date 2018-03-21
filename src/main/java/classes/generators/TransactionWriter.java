package classes.generators;

import classes.file_factories.FileWriterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.ZonedDateTime;

public class TransactionWriter {

    private static final Logger logger = LoggerFactory.getLogger(TransactionWriter.class);

    private final Path outDir;
    private final Gson gson;
    private final FileWriterFactory fileWriterFactory;

    public TransactionWriter(Path outDir, FileWriterFactory fileWriterFactory) {
        this.outDir = outDir;
        this.fileWriterFactory = fileWriterFactory;
        this.gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer()).create();
    }

    public void write(Transaction transaction) {
        Path transactionPath = getTransactionPath(transaction);
        logger.info("Writing " + transaction + " to file: " + transactionPath);
        try (Writer writer = fileWriterFactory.getFileWriter(transactionPath)) {
            writer.write(gson.toJson(transaction));
        } catch (IOException e) {
            String message = "Exception thrown during writing transaction to file. " + e.getMessage();
            logger.error(message);
            throw new FileWriteException(message);
        }
    }

    private Path getTransactionPath(Transaction transaction) {
        return outDir.resolve("transaction_" + transaction.getId() + ".json");
    }
}