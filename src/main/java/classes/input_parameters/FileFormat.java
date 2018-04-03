package classes.input_parameters;

public enum FileFormat {
    JSON, XML, YAML;

    public String getFileExtension() {
        return "." + this.toString().toLowerCase();
    }
}
