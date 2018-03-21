package classes.generators;

import java.util.stream.IntStream;

public class TransactionsGeneratorHandler {

    private final TransactionGenerator transactionGenerator;
    private final TransactionWriter transactionWriter;
    private final int eventCount;

    public TransactionsGeneratorHandler(TransactionGenerator transactionGenerator, TransactionWriter transactionWriter, int eventCount) {
        this.transactionGenerator = transactionGenerator;
        this.transactionWriter = transactionWriter;
        this.eventCount = eventCount;
    }

    public void handle() {
        IntStream.range(1, eventCount + 1).forEach(i -> transactionWriter.write(transactionGenerator.generate(i)));
    }
}
