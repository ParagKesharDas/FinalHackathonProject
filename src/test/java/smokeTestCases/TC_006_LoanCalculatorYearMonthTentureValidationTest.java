package smokeTestCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.LandingPage;
import pageObjects.LoanCalculatorPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_006_LoanCalculatorYearMonthTentureValidationTest extends BaseClass {
	@Test(priority = 0,groups = "smoke test")
	public void isNavigatedToLoanCalculator() throws IOException, InterruptedException {
		BasePage bp = new BasePage(driver);
		bp.logger.info(
				"*** Starting TC_006_LoanCalculatorYearMonthTentureValidationTest :isNavigatedToLoanCalculator ***");
		try {
			LandingPage landingPage = new LandingPage(driver);
			landingPage.clickMenuItemCalculator();
			landingPage.clickLoanCalculator();
			timeUnitSleep(5);
			System.out.println(landingPage.getPageTitle());
			if (landingPage.getPageTitle().contains("Loan Calculator — Calculate EMI, Affordability, Tenure & Interest Rate")) {
				bp.logger.info("Page title matched");
				Assert.assertTrue(true);
			} else {
				bp.logger.info("Page title mismatched");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			bp.logger.error(
					"TC_006_LoanCalculatorYearMonthTentureValidationTest :isNavigatedToLoanCalculator Failed due to "
							+ e);
			Assert.fail();
		}
	}

	@Test(priority = 1, dataProvider = "yearMonthData", dataProviderClass = DataProviders.class,groups = "smoke test")
	public void yearMonthBtnTesting(String year, String month) throws IOException {
		BasePage bp = new BasePage(driver);
		bp.logger.info("*** Starting TC_006_LoanCalculatorYearMonthTentureValidationTest :yearMonthBtnTesting ***");
		System.out.println("===============================================");
		System.out.println("Executing TC_006_LoanCalculatorYearMonthTentureValidationTest");
		System.out.println("===============================================");
		
		try {
			LoanCalculatorPage loanCalculatorPage = new LoanCalculatorPage(driver);
			timeUnitSleep(2);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", loanCalculatorPage.loanMonthLink);
			loanCalculatorPage.setLoanTenureInput(month);
			executor.executeScript("arguments[0].click();", loanCalculatorPage.loanYearLink);
//			loanCalculatorPage.scrollPoint1.click();
			timeUnitSleep(2);
			String str = loanCalculatorPage.getLoanTenure();
			if (str.contains(year)) {
				bp.logger.info("Month to Year Value Changing is correct");
				System.out.println("Month to Year Value Changing is correct");
				Assert.assertTrue(true);
			} else {
				bp.logger.info("Month to Year Value Changing is not correct");
				System.out.println("Month to Year Value Changing is not correct");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			bp.logger.error(
					"TC_006_LoanCalculatorYearMonthTentureValidationTest :yearMonthBtnTesting Failed due to " + e);
			Assert.fail();
		}
	}

}
