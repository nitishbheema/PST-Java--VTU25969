import org.junit.jupiter.api.*;
import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import static org.junit.jupiter.api.Assertions.*;

// -------------------- CLASS --------------------
class AccountService {

    private volatile float balance;

    public AccountService(float initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized Boolean deposit(int amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public synchronized Boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public synchronized Float getBalance() {
        return balance;
    }
}

// -------------------- TEST --------------------
class AccountServiceTest {

    AccountService account;

    @BeforeEach
    void setUp() {
        account = new AccountService(1000);
    }

    // ---- Basic Tests ----
    @Test
    void depositTest() {
        account.deposit(500);
        assertEquals(1500f, account.getBalance());
    }

    @Test
    void withdrawTest() {
        account.withdraw(300);
        assertEquals(700f, account.getBalance());
    }

    @Test
    void insufficientFunds() {
        assertFalse(account.withdraw(5000));
    }

    // ---- Concurrent Deposits ----
    @Test
    void concurrentDeposits() throws Exception {
        int threads = 100;
        ExecutorService ex = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            ex.submit(() -> account.deposit(10));
        }

        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(2000f, account.getBalance(), 0.1f);
    }

    // ---- Concurrent Withdrawals ----
    @Test
    void concurrentWithdrawals() throws Exception {
        int threads = 100;
        ExecutorService ex = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            ex.submit(() -> account.withdraw(50));
        }

        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);

        assertTrue(account.getBalance() >= 0);
    }

    // ---- Mixed Operations ----
    @Test
    void mixedOperations() throws Exception {
        int threads = 100;
        ExecutorService ex = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            int idx = i;
            ex.submit(() -> {
                if (idx % 2 == 0) account.deposit(100);
                else account.withdraw(50);
            });
        }

        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);

        assertTrue(account.getBalance() >= 0);
    }

    // ---- Stress Test ----
    @Test
    void stressTest() {
        assertTimeout(Duration.ofSeconds(10), () -> {

            AccountService acc = new AccountService(10000);
            ExecutorService ex = Executors.newFixedThreadPool(150);

            for (int i = 0; i < 150; i++) {
                int idx = i;
                ex.submit(() -> {
                    for (int j = 0; j < 100; j++) {
                        if (idx % 3 == 0) acc.deposit(10);
                        else if (idx % 3 == 1) acc.withdraw(5);
                        else acc.getBalance();
                    }
                });
            }

            ex.shutdown();
            ex.awaitTermination(10, TimeUnit.SECONDS);

            assertTrue(acc.getBalance() >= 0);
        });
    }
}
