package shift4.parser

class MonthParser : CronElementParser(1, 12, true) {
    override fun humanReadableParserName(): String = "month"
}
