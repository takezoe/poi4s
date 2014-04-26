java -Xmx512M -Xss2M -XX:+CMSClassUnloadingEnabled -jar `dirname $0`/sbt-launch-0.13.2.jar "$@"
