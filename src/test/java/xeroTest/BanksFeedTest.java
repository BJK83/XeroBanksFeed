package xeroTest;

import commonFunction.BankAccountMethods;
import commonFunction.LoginMethods;
import testData.BanksFeedTestData;

import org.testng.Assert;
import org.testng.annotations.*;

public class BanksFeedTest extends TestBase{
    static LoginMethods login=new LoginMethods();
    static BankAccountMethods bankAccounts= new BankAccountMethods();
    static String deleteAcctName;
    @BeforeClass
    public void beforeClass() throws Exception {
        login.loginXero("sowmya2709@gmail.com", "August1234");
    }




    @Test(dataProvider = "bankAccounts", dataProviderClass = BanksFeedTestData.class)
    public static void testAddBank(String accountName, String accountNumber, String accountType, String currencyType) throws Exception {
        deleteAcctName= accountName;
        bankAccounts.selectBankAccountMenu();
        bankAccounts.addButtonSelect();
        bankAccounts.addAccount(accountName, accountNumber, accountType, currencyType);
        bankAccounts.skipUploadfile(accountType);
        bankAccounts.verifyAccountDetailsOnDashboard( accountName, accountNumber);
    }
    
    @Test(dataProvider = "bankAccountsDuplicate",dataProviderClass = BanksFeedTestData.class)
    public static void testAddDuplicateBank(String accountName, String accountNumber, String accountType, String currencyType) throws Exception {
        deleteAcctName= accountName;
        bankAccounts.selectBankAccountMenu();
        bankAccounts.addButtonSelect();
        bankAccounts.addAccount(accountName, accountNumber, accountType, currencyType);
        bankAccounts.skipUploadfile(accountType);
        bankAccounts.verifyAccountDetailsOnDashboard(accountName, accountNumber);
        bankAccounts.selectBankAccountMenu();
        bankAccounts.addButtonSelect();
        boolean error = bankAccounts.addDuplicateAccount(accountName, accountNumber, accountType, currencyType);
        Assert.assertTrue(error);
    }




    @AfterMethod
    public void afterTest() throws Exception{
        bankAccounts.deleteAccount(deleteAcctName);
    }

    @AfterClass
    public void tearDown(){
    	login.logoutXero();
    }

}