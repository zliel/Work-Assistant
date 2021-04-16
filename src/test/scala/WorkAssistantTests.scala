import org.scalatest.{BeforeAndAfterAll, FunSuite}

import java.awt.Desktop

class WorkAssistantTests extends FunSuite with BeforeAndAfterAll {
  val assistant:WorkHelper = WorkHelper()

  test("WorkAssistant.browse methods should be supported") {
    assert(Desktop.isDesktopSupported)
  }

  test("WorkAssistant.calculateIncome should properly calculate income") {
    assert(assistant.calculateIncome(4) === 200)
  }

  test("WorkAssistant.searchViolin should compile properly") {
    assertCompiles("assistant.searchViolin(\"vibrato exercises\")")
  }

  test("WorkAssistant.searchImslp should compile properly") {
    assertCompiles("assistant.searchImslp(\"Chopin Nocturne in E-flat Major\")")
  }
}
