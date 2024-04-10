package shift4.util

fun printHelp(){
    print("""
            Invalid input, please insert proper cron expression followed by command
            Example: ./gradlew run --args="*/15 0 1,15 * 1-5 /usr/bin/find"
        """.trimIndent())
}