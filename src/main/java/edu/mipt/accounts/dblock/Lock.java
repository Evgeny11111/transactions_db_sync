package edu.mipt.accounts.dblock;

public class Lock {
    private static final Object lock = new Object();

    public void execute(Account first, Account second, Command command) {
        long firstId = first.getId();
        long secondId = second.getId();

        if (firstId < secondId) {
            lock(first, second, command);
            return;
        }

        if (secondId < firstId) {
            lock(second, first, command);
            return;
        }

        synchronized (lock) {
            lock(first, second, command);
        }
    }

    private void lock(Object externalLock, Object internalLock, Command command) {
        synchronized (externalLock) {
            synchronized (internalLock) {
                command.doTransfer();
            }
        }
    }
}
