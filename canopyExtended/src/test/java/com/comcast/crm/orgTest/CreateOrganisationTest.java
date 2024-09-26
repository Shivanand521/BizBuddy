package com.comcast.crm.orgTest;


import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.beseTest.BaseClass;
import com.comcast.crm.listnersImplementation.ListenersImplementation;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;
@Listeners(ListenersImplementation.class)
public class CreateOrganisationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrganisationTest() throws Exception {

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
		// Verify header message expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);

		String headerinfo =oip.getHeaderinfo().getText();// driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean status=headerinfo.contains(orgname);
		Assert.assertEquals(status, true);
		// Verify header orgname info expected result
		String actOrgName =oip.getOrgNameMessage().getText();
		Assert.assertEquals(actOrgName, orgname);
		
	}

	@Test(groups = "regressionTest")
	public void createOrganisationWithIndustryTest() throws Exception {
		String orgname = elib.getDataFromExcel("Org", 2, 2) + jlib.getRandomNumber();
		// navigate to organization page
		HomePage hp = new HomePage(driver);
		hp.getOrganisation().click();
		// Click on create Organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();
		// Enter data all= the details and create new organization
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.getOrgName().sendKeys(orgname);
		WebElement element = cno.getIndustry();
		wlib.selectOption(element, 3);
		cno.getSavebutton().click();
		// Verify header orgname info expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);

		String actOrgName =oip.getOrgNameMessage().getText();// driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		Assert.assertEquals(actOrgName, orgname);
		

	}

	@Test(groups = "regressionTest")
	public void createOrganisationWithPhNumberTest() throws Exception {
		String orgname = elib.getDataFromExcel("practise", 2, 2) + jlib.getRandomNumber();
		String phNum = elib.getDataFromExcel("practise", 1, 5);
		// navigate to organization page
		HomePage hp = new HomePage(driver);
		hp.getOrganisation().click();
		// Click on create Organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationButton().click();
		// Enter data all= the details and create new organization
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgname, phNum);
		// Verify header phoneNumber info expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actPhNum =oip.getMobNumMessage().getText();// driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
		Assert.assertEquals(actPhNum, phNum);
		
	}
}
