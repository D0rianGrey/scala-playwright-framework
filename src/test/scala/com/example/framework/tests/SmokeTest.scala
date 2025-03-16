package com.example.framework.tests

import com.example.framework.core.BaseBrowser
import com.example.framework.config.Config
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.typesafe.scalalogging.LazyLogging

class SmokeTest extends AnyFlatSpec with Matchers with BeforeAndAfterAll with LazyLogging {
  
  private val browser = new BaseBrowser()
  
  override def beforeAll(): Unit = {
    browser.initBrowser()
  }
  
  override def afterAll(): Unit = {
    browser.closeBrowser()
  }
  
  "Playwright" should "открыть браузер и загрузить сайт SauceDemo" in {
    logger.info("Запуск тестового метода")
    
    val page = browser.getPage
    page.navigate(Config.BaseUrl)
    
    val title = page.title()
    logger.info(s"Заголовок страницы: $title")
    
    title should include("Swag Labs")
    
    logger.info("Тест успешно завершен")
  }
}