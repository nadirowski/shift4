package shift4.parser

class DayOfWeekParser : CronElementParser(0,7, false){
    override fun humanReadableParserName(): String = "day of week"
}
