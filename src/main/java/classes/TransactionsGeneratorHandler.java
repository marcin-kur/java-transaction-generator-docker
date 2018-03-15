package classes;

import java.io.IOException;

public class TransactionsGeneratorHandler {

    private final TransactionGenerator transactionGenerator;
    private final TransactionWriter transactionWriter;
    private final int eventCount;

    public TransactionsGeneratorHandler(TransactionGenerator transactionGenerator, TransactionWriter transactionWriter, int eventCount) {
        this.transactionGenerator = transactionGenerator;
        this.transactionWriter = transactionWriter;
        this.eventCount = eventCount;
    }

    public void handle() throws IOException {
        for (int i = 1; i <= eventCount; i++) {
            transactionWriter.write(transactionGenerator.generate(i));
        }
    }
}
