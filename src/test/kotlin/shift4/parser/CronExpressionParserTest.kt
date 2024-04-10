package shift4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shift4.parser.model.CronParserResult
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CronExpressionParserTest {
    private val cronElementParser:CronExpressionParser = CronExpressionParser()

    @Test
    fun shouldParseExpressionAsSingleEntry(){
        val result = cronElementParser.parse("1 2 3 4 5 command")
        assertTrue { result is CronParserResult.Success }
        val success = result as CronParserResult.Success
        assertEquals( "1", success.minutes)
        assertEquals( "2", success.hours)
        assertEquals( "3", success.dayOfMonth)
        assertEquals( "4", success.month)
        assertEquals( "5", success.dayOfWeek)
        assertEquals( "command", success.command)
    }

    @Test
    fun shouldParseComplesExpression(){
        val result = cronElementParser.parse("*/15 0 1,15 * 1-5 /usr/bin/find")
        assertTrue { result is CronParserResult.Success }
        val success = result as CronParserResult.Success
        assertEquals( "0 15 30 45", success.minutes)
        assertEquals( "0", success.hours)
        assertEquals( "1 15", success.dayOfMonth)
        assertEquals( "1 2 3 4 5 6 7 8 9 10 11 12", success.month)
        assertEquals( "1 2 3 4 5", success.dayOfWeek)
        assertEquals( "/usr/bin/find", success.command)
    }

    @Test
    fun shouldParseExpressionAsMultipleEntries(){
        val result = cronElementParser.parse(listOf("1","2","3","4","5","command"))
        assertTrue { result is CronParserResult.Success }
        val success = result as CronParserResult.Success
        assertEquals( "1", success.minutes)
        assertEquals( "2", success.hours)
        assertEquals( "3", success.dayOfMonth)
        assertEquals( "4", success.month)
        assertEquals( "5", success.dayOfWeek)
        assertEquals( "command", success.command)
    }

    @Test
    fun shouldReturnErrorForTooLongExpression(){
        val result = cronElementParser.parse("1 2 3 4 5 command more commands")
        assertTrue { result is CronParserResult.Error }
    }

    @Test
    fun shouldReturnErrorForTooShortExpression(){
        val result = cronElementParser.parse("1 2 3 4")
        assertTrue { result is CronParserResult.Error }
    }

    @Test
    fun shouldReturnErrorForUnparsableExpression(){
        val result = cronElementParser.parse("-1 2 3 4 89 x")
        assertTrue { result is CronParserResult.Error }
    }

}