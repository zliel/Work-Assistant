import java.time.LocalDate
import scala.io.StdIn.{readInt, readLine}
import java.time.format.DateTimeFormatter
import java.awt.Desktop
import java.net.URI

object WorkAssistant extends App {
  val date = LocalDate.now
  val formatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
  println("Hey, how's it going?")
  println(f"Today is ${date.format(formatter)}")

  val workHelper : WorkHelper = WorkHelper()
  println("What would you like to do today?")
  val response : String = readLine()

  workHelper.handleSelection(response)
}

case class WorkHelper() {

  def handleSelection(response:String):Unit = {
    response.toLowerCase match {
      case "calculate income" =>
        println("How many students do you have each week?")
        val numOfStudents : Int = readInt()
        calculateIncome(numOfStudents)

      case "search violin" =>
        println("Okay, what would you like to search Violinist.com for?")
        val searchTerm : String = readLine().split(" ").map(str => str + "%20").mkString("")
        searchViolin(searchTerm)

      case "search imslp" =>
        println("Okay, what would you like to search IMSLP for?")
        val searchTerm : String = readLine().split(" ").map(str => str + "%20").mkString("")
        searchImslp(searchTerm)

      case _ => help()
    }
  }

  /**
   * Calculates the maximum income for the month using the number of students passed in as an
   * argument
   *
   * @param numberOfStudents The number of students to be used in the calculation, retrieved from
 *                           the [[WorkHelper#handleSelection()]] method
   * @return A double representing the maximum possible income for the month
   */
  def calculateIncome(numberOfStudents:Int): Double = {
    println("Processing . . . ")
    val income : Double = (numberOfStudents * 12.5) * 4

    println(f"Your max income this month is $income")
    income
  }

  /**
   * Searches Violinist.com for the specified search term and displays the search page in the user's preferred browser
   *
   * @param searchTerm The specified term to search for, passed in by the
   *                   [[WorkHelper#handleSelection()]] method. It will be appended to a url to
   *                   be passed into the [[WorkHelper#openPageInBrowser()]] method.
   */
  def searchViolin(searchTerm:String): Unit = {
    val url : String = f"https://violinist.com/search.cfm?q=$searchTerm"

    println("Sounds good, I'll bring that right up!")
    println("Processing . . . ")
    openPageInBrowser(url)
  }

  /**
   * Searches IMSLP for requested search term and opens up the search page in the user's preferred browser
   *
   * @param searchTerm The specified term to search for, passed in by the
   *                   [[WorkHelper#handleSelection()]] method. It will be appended to a url to
   *                   be passed into the [[WorkHelper#openPageInBrowser()]] method.
   */
  def searchImslp(searchTerm:String): Unit = {
    val url : String = f"https://www.google.com/search?q=site:imslp.org+$searchTerm"

    println("I'll pull that right up!")
    println("Processing . . . ")
    openPageInBrowser(url)
  }

  def openPageInBrowser(url:String): Unit = {
      if(Desktop.isDesktopSupported) {
      Desktop.getDesktop.browse(new URI(url))
    }
  }

  def help(): Unit = {
    val commands : List[String] = List("calculate income", "search violin", "search imslp")
    println("Possible commands: ")
    for(command <- commands) println(command)
  }
}