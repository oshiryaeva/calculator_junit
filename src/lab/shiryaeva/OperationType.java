package lab.shiryaeva;

public enum OperationType {

    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), XOR("^"), NONE("");

    private final String operation;

    OperationType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
