name := """apiserver"""

version := "0.1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, DebianPlugin, JavaServerAppPackaging)

maintainer in Linux := "Ashutosh Mishra <ashutosh@podkart.com>"

packageSummary in Linux := "Splitr API server"

packageDescription := "Splitr API server"

scalaVersion := "2.11.1"

dockerExposedPorts in Docker := Seq(9000)
 
dockerRepository := Some("ashutoshpodkart")

libraryDependencies ++= Seq(
  "be.objectify"  %% "deadbolt-java"     % "2.3.0-RC1",
  "com.feth"      %% "play-authenticate" % "0.6.5-SNAPSHOT",
  "org.mongodb" % "mongo-java-driver" % "2.13.0",
  "org.mongodb.morphia" % "morphia" % "0.110",
  "org.mongodb.morphia" % "morphia-logging-slf4j" % "0.110",
  "org.mongodb.morphia" % "morphia-validation" % "0.110",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.5.1",
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.1.RELEASE",
  "org.springframework" % "spring-expression" % "4.1.1.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.1.RELEASE",
  "org.springframework" % "spring-test" % "4.1.1.RELEASE" % "test",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.5.3", 
  "com.atlassian.event" % "atlassian-event" % "3.1.3",
  "com.atlassian.jira" % "jira-rest-java-client-api" % "3.0.0",
  "com.atlassian.util.concurrent" % "atlassian-util-concurrent" % "3.0.0",
  "com.google.code.findbugs" % "jsr305" % "3.0.1",
  "com.google.guava" % "guava" % "19.0",
  "com.sun.jersey" % "jersey-client" % "1.19.1",
  "com.sun.jersey" % "jersey-json" % "1.19.1",
  "com.sun.jersey" % "jersey-core" % "1.19.1",
  "com.sun.jersey.contribs" % "jersey-apache-client" % "1.19.2",
  "joda-time" % "joda-time" % "2.9.4",
  "org.apache.httpcomponents" % "httpmime" % "4.5.2",
  "org.springframework" % "spring-beans" % "4.3.2.RELEASE",
  "org.codehaus.jettison" % "jettison" % "1.3.8",
  "com.sun.xml.bind" % "jaxb-impl" % "2.2.7",
  "javax.activation" % "activation" % "1.1.1",
  "commons-codec" % "commons-codec" % "1.10",
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "commons-logging" % "commons-logging" % "1.2",
  "javax.ws.rs" % "jsr311-api" % "1.1.1",
  "javax.xml.bind" % "jaxb-api" % "2.2.3",
  "stax" % "stax" % "1.2.0",
  "org.codehaus.jackson" % "jackson-core-asl" % "1.9.13",
  "com.atlassian.jira" % "jira-rest-java-client-core" % "3.0.0",  
  "com.atlassian.jira" % "jira-rest-java-client-api" % "3.0.0",
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)


resolvers ++= Seq(
  "Apache" at "http://repo1.maven.org/maven2/",
  "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
  "play-easymail (release)" at "http://joscha.github.io/play-easymail/repo/releases/",
  "play-easymail (snapshot)" at "http://joscha.github.io/play-easymail/repo/snapshots/",
  Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns),
  "play-authenticate (release)" at "http://joscha.github.io/play-authenticate/repo/releases/",
  "play-authenticate (snapshot)" at "http://joscha.github.io/play-authenticate/repo/snapshots/",
  "spring-milestones" at "http://repo.spring.io/libs-release-remote",
  "Maven Central Server" at "http://repo1.maven.org/maven2",
  "Maven JDJC" at "https://maven.atlassian.com/content/repositories/atlassian-public/",
  "Dependecy Maven JDJC" at "https://www.versioneye.com/java/",
  "Dependency HttpClient" at "http://jcenter.bintray.com/",
  "Jersey Apache" at "https://mvnrepository.com/artifact/com.sun.jersey/jersey-client"
  )

TaskKey[Unit]("stop") := {
  val pidFile = target.value / "universal" / "stage" / "RUNNING_PID"
  if (!pidFile.exists) throw new Exception("App not started!")
  val pid = IO.read(pidFile)
  s"kill $pid".!
  println(s"Stopped application with process ID $pid")
}
