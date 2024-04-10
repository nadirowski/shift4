package shift4.parser

import shift4.parser.model.CronParserResult
import shift4.util.printHelp
import java.lang.IllegalArgumentException

class CronExpressionParser {
    private val minuteParser: MinuteParser = MinuteParser()
    private val hourParser: HourParser = HourParser()
    private val dayOfMonthParser: DayOfMonthParser = DayOfMonthParser()
    private val monthParser: MonthParser = MonthParser()
    private val dayOfWeekParser: DayOfWeekParser = DayOfWeekParser()

    fun parse(expression: String): CronParserResult = parse(expression.split(" "))

    fun parse(expressionParts: List<String>): CronParserResult =
        if (validInput(expressionParts)) {
            try {
                val minutes = minuteParser.parse(expressionParts[0])
                val hours = hourParser.parse(expressionParts[1])
                val dayOfMonth = dayOfMonthParser.parse(expressionParts[2])
                val month = monthParser.parse(expressionParts[3])
                val dayOfWeek = dayOfWeekParser.parse(expressionParts[4])
                CronParserResult.Success(minutes, hours, dayOfMonth, month, dayOfWeek, expressionParts[5])
            } catch (ex: IllegalArgumentException) {
                CronParserResult.Error("Unexpected input: ${ex.message}")
            } catch (ex: NumberFormatException) {
                CronParserResult.Error("Unexpected input: ${ex.message}")
            }
        } else {
            CronParserResult.Error("Unexpected input: should contain 6 parts")
        }

    private fun validInput(parts: List<String>): Boolean = parts.size == 6
}