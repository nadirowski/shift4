package shift4.parser

class DayOfMonthParser : CronElementParser(1, 31, true){
    override fun humanReadableParserName(): String = "day of month"
}