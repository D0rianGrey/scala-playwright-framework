package com.example.framework.core

import com.example.framework.config.Config
import com.microsoft.playwright._
import com.typesafe.scalalogging.LazyLogging

import java.nio.file.Paths

class BaseBrowser extends LazyLogging {
  private var playwright: Playwright = _
  private var browser: Browser = _
  private var context: BrowserContext = _
  private var page: Page = _

  def initBrowser(): this.type = {
    logger.info(s"Инициализация браузера: ${Config.Browser.Type}")

    playwright = Playwright.create()

    val launchOptions = new BrowserType.LaunchOptions()
      .setHeadless(Config.Browser.Headless)
      .setSlowMo(Config.Browser.SlowMo)

    browser = Config.Browser.Type.toLowerCase match {
      case "chromium" => playwright.chromium().launch(launchOptions)
      case "firefox"  => playwright.firefox().launch(launchOptions)
      case "webkit"   => playwright.webkit().launch(launchOptions)
      case _ =>
        logger.warn(
          s"Неизвестный тип браузера: ${Config.Browser.Type}, используется chromium"
        )
        playwright.chromium().launch(launchOptions)
    }

    context = browser.newContext(
      new Browser.NewContextOptions()
        .setViewportSize(1920, 1080)
        .setRecordVideoDir(Paths.get("videos/"))
    )

    page = context.newPage()
    page.setDefaultTimeout(Config.Timeouts.DefaultTimeout)
    page.setDefaultNavigationTimeout(Config.Timeouts.NavigationTimeout)

    this
  }

  def getPage: Page = page

  def closeBrowser(): Unit = {
    Option(context).foreach(_.close())
    Option(browser).foreach(_.close())
    Option(playwright).foreach(_.close())
    logger.info("Браузер закрыт")
  }
}
