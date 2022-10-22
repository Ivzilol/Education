import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChainblockImplTest {
    private Chainblock database;
    private List<Transaction> transactions;

    @Before
    public void setup() {
        this.database = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        pepearTransactionForTest();
    }

    private void pepearTransactionForTest() {
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                "Desi", "Stoyan", 150.90);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL,
                "Desi", "Emil", 120.90);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED,
                "Petar", "Emil", 100.90);
        this.transactions.add(transaction1);
        this.transactions.add(transaction2);
        this.transactions.add(transaction3);
    }

    @Test
    public void testAddCorrectTransaction() {
        Assert.assertEquals(0, this.database.getCount());
        this.database.add(transactions.get(0));
        Assert.assertEquals(1, this.database.getCount());
    }

    @Test
    public void testAddExistingTransaction() {
        Assert.assertEquals(0, this.database.getCount());
        this.database.add(transactions.get(0));
        Assert.assertEquals(1, this.database.getCount());
        this.database.add(transactions.get(0));
        Assert.assertEquals(1, this.database.getCount());


    }

    @Test
    public void testContains() {
        Assert.assertFalse(this.database.contains(transactions.get(0)));
        Assert.assertFalse(this.database.contains(transactions.get(0).getId()));
        this.database.add(transactions.get(0));
        Assert.assertTrue(this.database.contains(transactions.get(0)));
        Assert.assertTrue(this.database.contains(transactions.get(0).getId()));
    }

    @Test
    public void testChangeTransactionStatus() {
        this.database.add(transactions.get(0));
        Assert.assertEquals(1, this.database.getCount());
        this.database.changeTransactionStatus(1, TransactionStatus.ABORTED);
        Assert.assertEquals(TransactionStatus.ABORTED, transactions.get(0).getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusInvalidId() {
        this.database.add(transactions.get(0));
        Assert.assertEquals(1, this.database.getCount());
        this.database.changeTransactionStatus(2, TransactionStatus.ABORTED);
    }

    @Test
    public void testRemoveTransactionById() {
        this.database.add(this.transactions.get(0));
        this.database.add(this.transactions.get(1));
        Assert.assertEquals(2, this.database.getCount());
        int id = this.transactions.get(0).getId();
        this.database.removeTransactionById(id);
        Assert.assertEquals(1, this.database.getCount());
        Assert.assertFalse(this.database.contains(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionBIdThrowInvalidId() {
        this.database.add(this.transactions.get(0));
        this.database.removeTransactionById(5);
    }

    @Test
    public void testGetById() {
        Transaction transaction = this.transactions.get(0);
        this.database.add(transaction);
        Transaction returnedId = this.database.getById(transaction.getId());
        Assert.assertEquals(transaction, returnedId);
        Assert.assertEquals(transaction.getId(), returnedId.getId());
        Assert.assertEquals(transaction.getStatus(), returnedId.getStatus());
        Assert.assertEquals(transaction.getTo(), returnedId.getTo());
        Assert.assertEquals(transaction.getTo(), returnedId.getTo());
        Assert.assertEquals(transaction.getFrom(), returnedId.getFrom());
        Assert.assertEquals(transaction.getAmount(), returnedId.getAmount(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdThrowInvalidId() {
        this.database.add(this.transactions.get(0));
        this.database.getById(5);

    }

    private void fillDatabaseWithTransactions() {
        this.database.add(this.transactions.get(2));
        this.database.add(this.transactions.get(1));
        this.database.add(this.transactions.get(0));
    }

    @Test
    public void testGetByTransactionStatus() {
        List<Transaction> successfulTransaction = this.transactions
                .stream()
                .filter(tr -> tr.getStatus() == TransactionStatus.SUCCESSFUL)
                .collect(Collectors.toList());
        fillDatabaseWithTransactions();
        Iterable<Transaction> result = this.database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> returnedTransactions = new ArrayList<>();
        result.forEach(returnedTransactions::add);
        Assert.assertEquals(successfulTransaction.size(), returnedTransactions.size());
        returnedTransactions.forEach(tr -> Assert.assertEquals(TransactionStatus.SUCCESSFUL, tr.getStatus()));
        Assert.assertEquals(successfulTransaction, returnedTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusThrowInvalidStatus() {
        fillDatabaseWithTransactions();
        this.database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void testGetAllSendersWithTransactionsStatus () {
        fillDatabaseWithTransactions();
        this.database.add( new TransactionImpl(4,TransactionStatus.SUCCESSFUL,  "Petar", "Desi", 980.40));
        this.database.add( new TransactionImpl(5,TransactionStatus.ABORTED,  "Petar", "Desi", 980.40));
        Iterable<String> result = this.database.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<String> returnedSenders = new ArrayList<>();
        result.forEach(returnedSenders::add);
        Assert.assertEquals(3, returnedSenders.size());
        Assert.assertEquals("Petar", returnedSenders.get(0));
        Assert.assertEquals("Desi", returnedSenders.get(1));
        Assert.assertEquals("Desi", returnedSenders.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionsStatusThrowInvalidStatus() {
        fillDatabaseWithTransactions();
        this.database.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    //TODO:

    @Test
    public void testGetAllOrderedByAmountDescendingThenById(){
        fillDatabaseWithTransactions();
        Iterable<Transaction> result = this.database.getAllOrderedByAmountDescendingThenById();
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = this.transactions
                .stream()
                .sorted(Comparator.comparing((Transaction::getAmount)).reversed()
                        .thenComparing(Transaction::getId)).collect(Collectors.toList());
        Assert.assertEquals(expected, returned);
    }
}