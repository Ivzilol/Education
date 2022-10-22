import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    private Map<Integer, Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
    }

    public int getCount() {
        return this.transactionMap.size();
    }

    public void add(Transaction transaction) {
        int id = transaction.getId();
        if (!transactionMap.containsKey(id)) {
            this.transactionMap.put(id, transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return this.transactionMap.containsValue(transaction);
    }

    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            Transaction transactionForChange = this.transactionMap.get(id);
            transactionForChange.setStatus(newStatus);
        }
    }

    public void removeTransactionById(int id) {
        if (!this.transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            this.transactionMap.remove(id);
        }
    }

    public Transaction getById(int id) {
        if (!this.transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        } else {
            return this.transactionMap.get(id);
        }
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : this.transactionMap.values()) {
            if (transaction.getStatus() == status) {
                filteredTransactions.add(transaction);
            }
        }
        if (filteredTransactions.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            filteredTransactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
            return filteredTransactions;
        }
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransaction = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransaction::add);
        if (filteredTransaction.size() == 0) {
            throw new IllegalArgumentException();
        }
        filteredTransaction
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        List<String> senders = filteredTransaction
                .stream()
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransaction = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransaction::add);
        if (filteredTransaction.size() == 0) {
            throw new IllegalArgumentException();
        }
        filteredTransaction
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        List<String> receivers = filteredTransaction
                .stream()
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactionMap.values()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
