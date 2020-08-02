package testData;

import org.testng.annotations.DataProvider;

public class BanksFeedTestData {
    @DataProvider(name="bankAccounts")
    public Object[][] addBankAccounts() {
        return new Object[][]{
                {"B Hiremath", "4323", "Credit Card", "NZD"}, // Account Type - Credit Card
                {"S Hiremath", "12345678900987654321", "Everyday (day-to-day)", "NZD"}, //Account Type - Everyday (day-to-day)
                {"A Hiremath", "12345678900987654322", "Loan", "NZD"}, //Account Type - Loan
                {"C Hiremath", "12345678900987654324", "Other", "NZD"}, //Account Type - Other
                {"D Hiremath", "12345678900987654325", "Term Deposit", "NZD"}, // Account Type - Term Deposit
        };

    }

    @DataProvider(name="bankAccountsDuplicate")
    public Object[][] addBankAccountsDuplicate() {
        return new Object[][]{
                {"A Hiremath", "12345678900987654322", "Loan", "NZD"}, //Account Type - Loan - Add Duplicate Account
        };

    }
}
