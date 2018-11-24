import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class Handler extends RequestHandler[Request, Response] {
  def handleRequest(input: Request, context: Context): Response = {
    println("Hello world")
    Response("done", input)
  }
}
