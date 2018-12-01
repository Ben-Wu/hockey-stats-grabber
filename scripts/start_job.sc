import $ivy.`com.github.seratch::awscala-stepfunctions:0.8.+`
import $ivy.`org.json4s::json4s-native:3.6.2`

import awscala._, stepfunctions._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

implicit val steps = StepFunctions.at(Region.US_EAST_1)
val stateMachine = steps.stateMachine("nhl-stats-grabber")

if (stateMachine.isEmpty) {
  println("Provision resources and deploy code by running 'make deploy'")
} else {
  print("Start year of starting season: ")
  val startYear = scala.io.StdIn.readInt()
  print("Start year of ending season: ")
  val endYear = scala.io.StdIn.readInt()

  val executionInput = ("startYear" -> startYear) ~ ("endYear" -> endYear)

  val execution = stateMachine.get.startExecution(input = compact(render(executionInput)))
  println(s"Successfully started execution ${execution.name}")
}
