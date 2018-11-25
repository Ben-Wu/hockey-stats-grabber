package ca.benwu.model

import com.amazonaws.services.lambda.runtime.{ClientContext, CognitoIdentity, Context, LambdaLogger}

case class MockContext() extends Context {

  override def getAwsRequestId: String = ""

  override def getLogGroupName: String = ""

  override def getLogStreamName: String = ""

  override def getFunctionName: String = ""

  override def getFunctionVersion: String = ""

  override def getInvokedFunctionArn: String = ""

  override def getIdentity: CognitoIdentity = null

  override def getClientContext: ClientContext = null

  override def getRemainingTimeInMillis: Int = 0

  override def getMemoryLimitInMB: Int = 0

  override def getLogger: LambdaLogger = null
}
