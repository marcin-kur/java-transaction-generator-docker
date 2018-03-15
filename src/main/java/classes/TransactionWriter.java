package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.ZonedDateTime;

public class TransactionWriter {

    private final Path outDir;
    private final Gson gson;
    private final FileWriterFactory fileWriterFactory;

    public TransactionWriter(Path outDir, FileWriterFactory fileWriterFactory) {
        this.outDir = outDir;
        this.fileWriterFactory = fileWriterFactory;
        this.gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer()).create();
    }

    public void write(Transaction transaction) throws IOException {
        Writer writer = fileWriterFactory.getFileWriter(getTransactionPath(transaction));
        writer.write(gson.toJson(transaction));
        writer.close();
    }

    private Path getTransactionPath(Transaction transaction) {
        return outDir.resolve("transaction_" + transaction.getId() + ".json");
    }
}
