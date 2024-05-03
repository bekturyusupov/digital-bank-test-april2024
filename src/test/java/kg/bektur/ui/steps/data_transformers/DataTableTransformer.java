package kg.bektur.ui.steps.data_transformers;

import io.cucumber.java.DataTableType;
import kg.bektur.ui.models.*;

import java.util.Map;

public class DataTableTransformer {
    @DataTableType
    public CheckingAccount checkingAccount(Map<String, String> entry) {
        String accountType = entry.get("accountType");
        String accountOwnership = entry.get("accountOwnership");
        String accountName = entry.get("accountName");
        double initialDeposit = Double.parseDouble(entry.get("initialDeposit"));

        return new CheckingAccount(accountType, accountOwnership, accountName, initialDeposit);
    }

    @DataTableType
    public AccountCard accountCard(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String ownership = entry.get("ownership");
        long accountNumber = Long.valueOf(entry.get("accountNumber"));
        String interestRate = entry.get("interestRate");
        double balance = Double.valueOf(entry.get("balance"));

        return new AccountCard(accountName, accountType, ownership, accountNumber, interestRate, balance);
    }

    @DataTableType
    public TransactionsList transactionsList(Map<String, String> entry) {
        String date = entry.get("");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.valueOf(entry.get("amount"));
        double balance = Double.valueOf(entry.get("balance"));

        return new TransactionsList(date, category, description, amount, balance);
    }

    @DataTableType
    public ExternalAccount externalAccount(Map<String, String> entry) {
        String bankingProvider = entry.get("bankingProvider");
        String username = entry.get("username");
        String password = entry.get("password");

        return new ExternalAccount(bankingProvider, username, password);
    }
}
