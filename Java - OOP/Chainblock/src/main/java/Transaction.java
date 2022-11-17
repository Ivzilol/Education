public interface Transaction {
    int getId();

    TransactionStatus getStatus();

    void setStatus(TransactionStatus newStatus);
    String getTo();
    String getFrom();
    double getAmount();
}

