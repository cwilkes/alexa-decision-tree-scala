package example;

import scala.collection.JavaConverters._
import java.net.URLDecoder
import com.amazonaws.services.lambda.runtime.events.S3Event

case class NameInfo(firstName: String, lastName: String)


class Main {
  import java.io.{InputStream, OutputStream, PrintStream}
  def decodeS3Key(key: String): String = URLDecoder.decode(key.replace("+", " "), "utf-8")

  def getSourceBuckets(event: S3Event): java.util.List[String] = {
    val result = event.getRecords.asScala.map(record => decodeS3Key(record.getS3.getObject.getKey)).asJava
    println(result)
    return result
  }

  val scalaMapper = {
    import com.fasterxml.jackson.databind.ObjectMapper
    import com.fasterxml.jackson.module.scala.DefaultScalaModule
    new ObjectMapper().registerModule(new DefaultScalaModule)
  }
  def greeting(input: InputStream, output: OutputStream): Unit = {
    val name = scalaMapper.readValue(input, classOf[NameInfo])
    val result = s"Greetings ${name.firstName} ${name.lastName}."
    output.write(result.getBytes("UTF-8"))
  }
}
