package ca.benwu

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import model.{LambdaInput, LambdaResponse}

class Handler extends RequestHandler[LambdaInput, LambdaResponse] {
  def handleRequest(input: LambdaInput, context: Context): LambdaResponse = {
    println("Hello world")
    LambdaResponse("done", input)
  }
}
