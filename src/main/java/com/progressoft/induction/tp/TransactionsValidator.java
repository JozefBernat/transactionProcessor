package com.progressoft.induction.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public interface TransactionsValidator {
    List<Violation> validate();
    boolean isBalanced();

    class TransactionsValidatorImpl implements TransactionsValidator {

        private List<Transaction> transactionsList;

        public TransactionsValidatorImpl(List<Transaction> transactionList) {
            this.transactionsList = transactionList;
        }

        @Override
        public List<Violation> validate() {
            List<Violation> violationsList = new ArrayList<>();
            IntStream.range(0, transactionsList.size()).forEach(i -> violationsList.addAll(Transaction.toValidation(i, transactionsList.get(i)).isValid()));
            return violationsList;
        }

        @Override
        public boolean isBalanced() {
            return Transaction.sumOf(transactionsList, Transaction.TransactionType.D.toString()).equals(Transaction.sumOf(transactionsList, Transaction.TransactionType.C.toString()));
        }
    }
}
