package shift4.parser

class HourParser: CronElementParser(0,24, false) {
    override fun humanReadableParserName(): String = "hour"
}
