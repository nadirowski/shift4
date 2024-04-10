package shift4.parser.model

import shift4.parser.CronExpressionParser

sealed interface CronParserResult {
    data class Success(
        val minutes: String,
        val hours: String,
        val dayOfMonth: String,
        val month: String,
        val dayOfWeek: String,
        val command: String,
    ) : CronParserResult

    data class Error(val message: String) : CronParserResult
}