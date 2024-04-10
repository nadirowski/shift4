package shift4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.test.assertEquals

class HourParserTest {
    private val hourParser:HourParser = HourParser()

    @Test
    fun shouldParseSingleValue(){
        assertEquals( "1", hourParser.parse("1"))
    }

    @Test
    fun shouldParseRange(){
        assertEquals( "1 2 3 4 5", hourParser.parse("1-5"))
    }

    @Test
    fun shouldParseMultipleValues(){
        assertEquals( "1 2 3 4 5", hourParser.parse("1,2,3-5"))
    }

    @Test
    fun shouldParseIncrement(){
        assertEquals( "0 15", hourParser.parse("*/15"))
        assertEquals( "3 13 23", hourParser.parse("3/10"))
    }

    @Test
    fun shouldParseAll(){
        assertEquals( "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23", hourParser.parse("*"))
    }

    @Test
    fun shouldParseAny(){
        assertEquals( "<any>", hourParser.parse("?"))
    }

    @Test
    fun shouldFailToParseWords(){
        assertThrows<NumberFormatException>{ hourParser.parse("words") }
    }

    @Test
    fun shouldFailToParseToBigNumbers(){
        assertThrows<IllegalArgumentException>{ hourParser.parse("24") }
    }

    @Test
    fun shouldFailToParseToSmallNumbers(){
        assertThrows<IllegalArgumentException>{ hourParser.parse("-1") }
    }
}