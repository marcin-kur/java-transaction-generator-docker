package classes;

import java.nio.file.Path;

public class InputParametersCreator {

    private final InputValueFactory inputValueFactory;

    public InputParametersCreator(InputValueFactory inputValueFactory) {
        this.inputValueFactory = inputValueFactory;
    }

    public InputParameters create(CommandLineArgs commandLineArgs) {
        InputParameters inputParameters = new InputParameters(getItemsFile(commandLineArgs.getItemsFile()));
        setCustomerIds(inputParameters, commandLineArgs.getCustomerIds());
        setDateRange(inputParameters, commandLineArgs.getDateRange());
        setItemsCount(inputParameters, commandLineArgs.getItemsCount());
        setItemsQuantity(inputParameters, commandLineArgs.getItemsQuantity());
        setEventsCount(inputParameters, commandLineArgs.getEventsCount());
        setOutDir(inputParameters, commandLineArgs.getOutDir());
        return inputParameters;
    }

    private Path getItemsFile(String itemsFile) {
        return inputValueFactory.getMandatoryFile(itemsFile);
    }

    private void setCustomerIds(InputParameters inputParameters, String customerIds) {
        try {
            IntegerRange integerRange = inputValueFactory.getIntegerRange(customerIds);
            inputParameters.setCustomerIds(integerRange);
        } catch (InputParseException e) {
            // logging error
        }
    }

    private void setDateRange(InputParameters inputParameters, String dateRange) {
        try {
            TimestampRange timestampRange = inputValueFactory.getTimestampRange(dateRange);
            inputParameters.setDateRange(timestampRange);
        } catch (InputParseException e) {
            // logging error
        }
    }

    private void setItemsCount(InputParameters inputParameters, String itemsCount) {
        try {
            IntegerRange integerRange = inputValueFactory.getIntegerRange(itemsCount);
            inputParameters.setItemsCount(integerRange);
        } catch (InputParseException e) {
            // logging error
        }
    }

    private void setItemsQuantity(InputParameters inputParameters, String itemsQuantity) {
        try {
            IntegerRange integerRange = inputValueFactory.getIntegerRange(itemsQuantity);
            inputParameters.setItemsQuantity(integerRange);
        } catch (InputParseException e) {
            // logging error
        }
    }

    private void setEventsCount(InputParameters inputParameters, String eventsCount) {
        try {
            int intValue = inputValueFactory.getInteger(eventsCount);
            inputParameters.setEventsCount(intValue);
        } catch (InputParseException e) {
            // logging error
        }
    }

    private void setOutDir(InputParameters inputParameters, String outDir) {
        try {
            Path path = inputValueFactory.getDirectory(outDir);
            inputParameters.setOutDir(path);
        } catch (InputParseException e) {
            // logging error
        }
    }
}
