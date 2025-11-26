package demo.actionservice.kafka.outbox;

public enum OutboxStatus {
    NEW,
    SENT,
    FAILED
}
