package kg.bektur.ui.models;

public class CheckingAccount {
    private String accountType;
    private String accountOwnership;
    private String accountName;
    private double initialDeposit;

    public CheckingAccount(String accountType, String accountOwnership, String accountName, double initialDeposit) {
        this.accountType = accountType;
        this.accountOwnership = accountOwnership;
        this.accountName = accountName;
        this.initialDeposit = initialDeposit;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwnership() {
        return accountOwnership;
    }

    public void setAccountOwnership(String accountOwnership) {
        this.accountOwnership = accountOwnership;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }
}
