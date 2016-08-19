package com.cxria.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.adapter.util.SharedDriver.SharedType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@SharedDriver(deleteCookies=false,type=SharedType.PER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ATest extends FluentTest {
  private WebDriver driver;
  private String baseUrl = "http://localhost:8080/";

  @Override
  public WebDriver getDefaultDriver() {
    System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
    driver = new ChromeDriver();
    driver.manage().window().setSize(new Dimension(414, 736));
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    return driver;
  }
  
  @Test(expected=StaleElementReferenceException.class)
  public void aTestLogin() throws Exception {
    goTo(baseUrl + "/weixin/user/login");
    find("#phone").text("18010684335");
    find("#password").text("123456");
    find("#login").click();
    assertThat(find(".layermcont").getText(), is("登录成功！"));
  }

  @Test
  public void testMall() {
    goTo(baseUrl + "/channel/");
    find("#user_control_btn").find("li",2).click();
    findFirst(".buy_btn").click();
    find("#addNum").click();
    findFirst(".save_btn").click();
    findFirst(".menu_cell.margin").click();
    findFirst(".default_check").click();
    getDriver().navigate().back();
    find(".menu_cell",2).click();
    findFirst(".save_btn").click();
    findFirst(".layermbtn").find("span",1).click();
    await().atMost(1000).until(".inputBox").areDisplayed();
    fill(".inputBox>input").with("1","2","3","4","5","6");
    find(".myPlugin-btn").find("a",0).click();
  }

  @Test
  public void testUserCenter() {
    goTo(baseUrl + "/weixin/user/");
  }
  
  
}
