package Tiki;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import Common.Logger;
import Constant.Constant;

public class TikiTest extends TestBase{
	
	HomePage homePage = new HomePage();
	
	@Test
	public void TestCase001() {
		Logger.info("Test case 001: Verify the product information loaded correctly");
		String searchProduct = "Điện thoại";
		
		Logger.info("Step 1: Navigate to \"TIKI\" website");
		homePage.open(Constant.TIKI_URL);
		
		Logger.verify("Verify that: \"Tìm sản phẩm ...\" textbox is displayed");
		String expectedPlaceHolder = "Tìm sản phẩm, danh mục hay thương hiệu mong muốn ...";
		assertTrue(homePage.isSearchTextBoxDisplayed(), "Search textbox is not displayed as expected");
		assertEquals(homePage.getSearchPlaceHolderText(), expectedPlaceHolder, "'Tìm sản phẩm ...' textbox should be displayed as expected");
		
		Logger.verify("\"Tìm kiếm\" button is displayed");
		assertTrue(homePage.isSearchButtonDisplayed(), "\"Tìm kiếm\" button should be displayed");
		
		Logger.info("Step 2: On home page, enter value in \"Tìm sản phẩm ...\" textbox");
		Logger.info("Step 3: Click \"Tìm kiếm\" button");
		ProductSearchPage productSearchPage = homePage.searchProduct(searchProduct);
		
		Logger.verify("Verify that: Breadcrumb is \"Trang chủ > Điện thoại\"");
		assertTrue(productSearchPage.isBreadCrumbDisplayed("Trang chủ > Điện thoại"), "Breadcrumb 'Trang chủ > Điện thoại' should be displayed as expected");
		
		Logger.verify("Verify that: \"Kết quả tìm kiếm cho `Điện thoại`\" title is displayed");
		assertEquals(productSearchPage.getSearchResultTitle(), "Kết quả tìm kiếm cho `" +searchProduct+ "`", "Search title should be displayed as expected as expected");
		
		Logger.info("Step 4: Select any item from result grid");
		Product product = productSearchPage.getRandomProductInformation();
		ProductPage productPage = productSearchPage.selectProduct(product);
		
		Logger.verify("Verify that: The selected item is displayed correctly in details. (Name, Price)");
		assertEquals(product.getName(), productPage.getProductName(), "Product name should be as same as selected product");
		assertEquals(product.getPrice(), productPage.getProductPrice(), "Product price should be as same as selected product");
	}

}
