package shift4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.test.assertEquals

class MonthParserTest {
    private val monthParser:MonthParser = MonthParser()

    @Test
    fun shouldParseSingleValue(){
        assertEquals( "1", monthParser.parse("1"))
    }

    @Test
    fun shouldParseRange(){
        assertEquals( "1 2 3 4 5", monthParser.parse("1-5"))
    }

    @Test
    fun shouldParseMultipleValues(){
        assertEquals( "1 2 3 4 5", monthParser.parse("1,2,3-5"))
    }

    @Test
    fun shouldParseIncrement(){
        assertEquals( "0 3 6 9", monthParser.parse("*/3"))
        assertEquals( "8", monthParser.parse("8/5"))
    }

    @Test
    fun shouldParseAll(){
        assertEquals( "1 2 3 4 5 6 7 8 9 10 11 12", monthParser.parse("*"))
    }

    @Test
    fun shouldParseAny(){
        assertEquals( "<any>", monthParser.parse("?"))
    }

    @Test
    fun shouldFailToParseWords(){
        assertThrows<NumberFormatException>{ monthParser.parse("words") }
    }

    @Test
    fun shouldFailToParseToBigNumbers(){
        assertThrows<IllegalArgumentException>{ monthParser.parse("12") }
    }

    @Test
    fun shouldFailToParseToSmallNumbers(){
        assertThrows<IllegalArgumentException>{ monthParser.parse("-1") }
    }
}