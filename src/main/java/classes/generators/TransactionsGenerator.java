package classes.generators;

import classes.writers.FileWriter;

import java.util.stream.IntStream;

public class TransactionsGenerator {

    private final TransactionGenerator transactionGenerator;
    private final FileWriter fileWriter;
    private final int eventCount;

    public TransactionsGenerator(TransactionGenerator transactionGenerator, FileWriter fileWriter, int eventCount) {
        this.transactionGenerator = transactionGenerator;
        this.fileWriter = fileWriter;
        this.eventCount = eventCount;
    }

    public void generate() {
        IntStream.range(1, eventCount + 1).forEach(i -> fileWriter.write(transactionGenerator.generate(i)));
    }
}
