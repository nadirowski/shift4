package shift4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.test.assertEquals

class DayOfMonthParserTest {
    private val dayOfMonthParser:DayOfMonthParser = DayOfMonthParser()

    @Test
    fun shouldParseSingleValue(){
        assertEquals( "1", dayOfMonthParser.parse("1"))
    }

    @Test
    fun shouldParseRange(){
        assertEquals( "1 2 3 4 5", dayOfMonthParser.parse("1-5"))
    }

    @Test
    fun shouldParseMultipleValues(){
        assertEquals( "1 2 3 4 5", dayOfMonthParser.parse("1,2,3-5"))
    }

    @Test
    fun shouldParseIncrement(){
        assertEquals( "0 13 26", dayOfMonthParser.parse("*/13"))
        assertEquals( "8 20", dayOfMonthParser.parse("8/12"))
    }

    @Test
    fun shouldParseAll(){
        assertEquals( "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31", dayOfMonthParser.parse("*"))
    }

    @Test
    fun shouldParseAny(){
        assertEquals( "<any>", dayOfMonthParser.parse("?"))
    }

    @Test
    fun shouldFailToParseWords(){
        assertThrows<NumberFormatException>{ dayOfMonthParser.parse("words") }
    }

    @Test
    fun shouldFailToParseToBigNumbers(){
        assertThrows<IllegalArgumentException>{ dayOfMonthParser.parse("32") }
    }

    @Test
    fun shouldFailToParseToSmallNumbers(){
        assertThrows<IllegalArgumentException>{ dayOfMonthParser.parse("-1") }
    }
}