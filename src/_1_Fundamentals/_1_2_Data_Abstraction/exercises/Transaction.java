package _1_Fundamentals._1_2_Data_Abstraction.exercises;

import java.util.Objects;

/*****************************************************************************************************
 * <p>
 * 1.2.13 Using our implementation of Date as a model(page91), develop an implementation of Transaction.
 * 1.2.14 Using our implementation of equals() in Date as a model(page103), develop
 * implementations of equals() for Transaction.
 *
 ****************************************************************************************************/

public class Transaction implements Comparable<Transaction> {

    private final String who;
    private final SmartDate when;
    private final double amount;

    public Transaction(String who, SmartDate when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] split = transaction.split("\\s+");
        this.who = split[0];
        this.when = new SmartDate(split[1]);
        this.amount = Double.parseDouble(split[2]);
    }

    public String who() {
        return who;
    }

    public SmartDate when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction o) {
        if (when.isAfter(o.when))
            return 1;
        else if (when.isBefore(o.when))
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(who, that.who) && Objects.equals(when, that.when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(who, when, amount);
    }

    public static void main(String[] args) {
        Transaction transaction = new Transaction("Narek 9/26/2022 1005.67");
        System.out.println(transaction.who);
        System.out.println(transaction.when);
        System.out.println(transaction.amount);
    }
}
