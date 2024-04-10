package shift4

import shift4.parser.CronExpressionParser
import shift4.parser.model.CronParserResult
import shift4.util.printHelp

fun main(args: Array<String>) {
    if (args.size == 1) {
        printResult(CronExpressionParser().parse(args[0]))
    } else if (args.size == 6) {
        printResult(CronExpressionParser().parse(args.toList()))
    } else {
        printHelp()
    }
}

fun printResult(result: CronParserResult) = when (result) {
    is CronParserResult.Success -> print(
        """
                minute ${result.minutes}
                hour ${result.hours}
                day of month ${result.dayOfMonth}
                month ${result.month}
                day of week ${result.dayOfWeek}
                command ${result.command}
            """.trimIndent()
    )

    is CronParserResult.Error -> {
        print(result.message)
        printHelp()
    }
}
