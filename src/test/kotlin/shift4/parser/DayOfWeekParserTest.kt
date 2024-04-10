package shift4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.test.assertEquals

class DayOfWeekParserTest {
    private val dayOfWeekParser:DayOfWeekParser = DayOfWeekParser()

    @Test
    fun shouldParseSingleValue(){
        assertEquals( "1", dayOfWeekParser.parse("1"))
    }

    @Test
    fun shouldParseRange(){
        assertEquals( "1 2 3 4 5", dayOfWeekParser.parse("1-5"))
    }

    @Test
    fun shouldParseMultipleValues(){
        assertEquals( "1 2 3 4 5", dayOfWeekParser.parse("1,2,3-5"))
    }

    @Test
    fun shouldParseIncrement(){
        assertEquals( "0 2 4 6", dayOfWeekParser.parse("*/2"))
        assertEquals( "2 5", dayOfWeekParser.parse("2/3"))
    }

    @Test
    fun shouldParseAll(){
        assertEquals( "0 1 2 3 4 5 6", dayOfWeekParser.parse("*"))
    }

    @Test
    fun shouldParseAny(){
        assertEquals( "<any>", dayOfWeekParser.parse("?"))
    }

    @Test
    fun shouldFailToParseWords(){
        assertThrows<NumberFormatException>{ dayOfWeekParser.parse("words") }
    }

    @Test
    fun shouldFailToParseToBigNumbers(){
        assertThrows<IllegalArgumentException>{ dayOfWeekParser.parse("7") }
    }

    @Test
    fun shouldFailToParseToSmallNumbers(){
        assertThrows<IllegalArgumentException>{ dayOfWeekParser.parse("-1") }
    }
}