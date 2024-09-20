package com.comcast.crm.orgTest;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.beseTest.BaseClass;
import com.comcast.crm.listnersImplementation.ListenersImplementation;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;
@Listeners(ListenersImplementation.class)
public class DeleteOrg_Test extends BaseClass {
	@Test(groups = "smokeTest")
	public void delete() throws Exception {

		String orgname = elib.getDataFromExcel("practise", 1, 1) + jlib.getRandomNumber();
		// navigate to organization page

		HomePage hp = new HomePage(driver);
		hp.getOrganisation().click();
		// Click on create Organization page

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();

		// Enter data all= the details and create new organization
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgname);
		Thread.sleep(4900);

		// Go back to Organization page
		hp.getOrganisation().click();

		// Search for Organization
		op.getSearchBox().sendKeys(orgname);
		wlib.selectByValue(op.getsearchDropDown(), "accountname");
		op.getSearchBtn().click();
		// In dynamic webtable select and delete Organization
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/../../td[8]/a[text()='del']")).click();
		wlib.switchToAlertAndAccept(driver);

	}
	@Test
	public void deleteWithPhNumber() {
		System.out.println("Deleted");
	}
}
