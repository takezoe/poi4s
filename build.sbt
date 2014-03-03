name := "poi4s"

organization := "jp.sf.amateras"

version := "0.0.1"

scalaVersion := "2.10.3"

resolvers += "amateras-release-repo" at "http://amateras.sourceforge.jp/mvn/"

resolvers += "amateras-snapshot-repo" at "http://amateras.sourceforge.jp/mvn-snapshot/"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  "org.apache.poi" % "poi" % "3.9"
)

parallelExecution in Test := false

scalacOptions := Seq("-feature")

publishTo <<= (version) { version: String =>
  val repoInfo =
    if (version.trim.endsWith("SNAPSHOT"))
      ("amateras snapshots" -> "/home/groups/a/am/amateras/htdocs/mvn-snapshot/")
    else
      ("amateras releases" -> "/home/groups/a/am/amateras/htdocs/mvn/")
  Some(Resolver.ssh(
    repoInfo._1,
    "shell.sourceforge.jp",
    repoInfo._2) as(System.getProperty("user.name"), (Path.userHome / ".ssh" / "id_rsa").asFile) withPermissions("0664"))
}