package classes.writers;

import classes.file_factories.FileWriterFactory;
import classes.model.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

@Slf4j
public class FileWriter {

    private static final String FILE_PREFIX = "transaction_";

    private final Path outDir;
    private final FileWriterFactory fileWriterFactory;
    private final FileFormatWriter fileFormatWriter;

    public FileWriter(Path outDir, FileWriterFactory fileWriterFactory, FileFormatWriter fileFormatWriter) {
        this.outDir = outDir;
        this.fileWriterFactory = fileWriterFactory;
        this.fileFormatWriter = fileFormatWriter;
    }

    public void write(Transaction transaction) {
        Path transactionPath = getTransactionPath(transaction);
        log.info("Writing transaction: ", transactionPath, transaction);
        try (Writer writer = fileWriterFactory.getFileWriter(transactionPath)) {
            writer.write(fileFormatWriter.getTransaction(transaction));
        } catch (IOException e) {
            log.error("Exception thrown during writing transaction to file. ", e);
            throw new FileWriteException("Exception thrown during writing transaction to file. ", e);
        }
    }

    private Path getTransactionPath(Transaction transaction) {
        return outDir.resolve(FILE_PREFIX + transaction.getId() + fileFormatWriter.getFileExtension());
    }
}