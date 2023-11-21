public enum OrderStatus {
    PROCESSING("In Bearbeitung"),
    IN_DELIVERY("Wird geliefert"),
    COMPLETED("Abgeschlossen");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}