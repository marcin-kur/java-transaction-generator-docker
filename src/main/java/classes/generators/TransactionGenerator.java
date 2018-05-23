package classes.generators;

import classes.model.Transaction;

public class TransactionGenerator {

    private final IntegerGenerator customerIdGenerator;
    private final TimestampGenerator timestampGenerator;
    private final ItemsGenerator itemsGenerator;

    public TransactionGenerator(IntegerGenerator customerIdGenerator, TimestampGenerator timestampGenerator, ItemsGenerator itemsGenerator) {
        this.customerIdGenerator = customerIdGenerator;
        this.timestampGenerator = timestampGenerator;
        this.itemsGenerator = itemsGenerator;
    }

    public Transaction generate(int id) {
        return new Transaction(
                id,
                customerIdGenerator.generate(),
                timestampGenerator.generate(),
                itemsGenerator.generate()
        );
    }
}
