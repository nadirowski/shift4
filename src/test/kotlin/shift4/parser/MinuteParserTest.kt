package shift4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.test.assertEquals

class MinuteParserTest {
    private val minuteParser:MinuteParser = MinuteParser()

    @Test
    fun shouldParseSingleValue(){
        assertEquals( "1", minuteParser.parse("1"))
    }

    @Test
    fun shouldParseRange(){
        assertEquals( "1 2 3 4 5", minuteParser.parse("1-5"))
    }

    @Test
    fun shouldParseMultipleValues(){
        assertEquals( "1 2 3 4 5", minuteParser.parse("1,2,3-5"))
    }

    @Test
    fun shouldParseIncrement(){
        assertEquals( "0 15 30 45", minuteParser.parse("*/15"))
        assertEquals( "8 28 48", minuteParser.parse("8/20"))
    }

    @Test
    fun shouldParseAll(){
        assertEquals( "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59", minuteParser.parse("*"))
    }

    @Test
    fun shouldParseAny(){
        assertEquals( "<any>", minuteParser.parse("?"))
    }

    @Test
    fun shouldFailToParseWords(){
        assertThrows<NumberFormatException>{ minuteParser.parse("words") }
    }

    @Test
    fun shouldFailToParseToBigNumbers(){
        assertThrows<IllegalArgumentException>{ minuteParser.parse("60") }
    }

    @Test
    fun shouldFailToParseToSmallNumbers(){
        assertThrows<IllegalArgumentException>{ minuteParser.parse("-1") }
    }
}