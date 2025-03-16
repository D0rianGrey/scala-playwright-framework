package com.example.framework.config

import com.typesafe.config.{Config => TypesafeConfig, ConfigFactory}

object Config {
  private val config: TypesafeConfig = ConfigFactory.load()

  val BaseUrl: String = config.getString("test.baseUrl")

  object Browser {
    val Type: String = config.getString("test.browser.type")
    val Headless: Boolean = config.getBoolean("test.browser.headless")
    val SlowMo: Int = config.getInt("test.browser.slowMo")
  }

  object Users {
    val StandardUsername: String = config.getString("test.users.standard.username")
    val StandardPassword: String = config.getString("test.users.standard.password")
    val LockedUsername: String = config.getString("test.users.locked.username")
    val LockedPassword: String = config.getString("test.users.locked.password")
    val ProblemUsername: String = config.getString("test.users.problem.username")
    val ProblemPassword: String = config.getString("test.users.problem.password")
  }

  object Timeouts {
    val DefaultTimeout: Int = config.getInt("test.timeouts.defaultTimeout")
    val NavigationTimeout: Int = config.getInt("test.timeouts.navigationTimeout")
  }
}