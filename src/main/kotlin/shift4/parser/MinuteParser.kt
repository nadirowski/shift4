package shift4.parser

class MinuteParser : CronElementParser(0,60, false) {
    override fun humanReadableParserName(): String = "minute"
}
