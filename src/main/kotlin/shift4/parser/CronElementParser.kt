package shift4.parser

abstract class CronElementParser(
    private val lowerBoundRangeValue: Int,
    private val upperBoundRangeValue: Int,
    private val includeUpperBound: Boolean,
) {

    fun parse(input: String): String {
        val splitted = input.split(",")
        var result = "";
        for (value in splitted) {
            result += parseSingle(value) + " "
        }
        return result.trim()
    }

    private fun parseSingle(value: String): String {
        if (isIncrementalValue(value)) {
            return parseIncremental(value, upperBoundRangeValue)
        }
        if (isAny(value)) {
            return "<any>"
        }
        if (isAll(value)) {
            return allRangeValues()
        }
        if (isRange(value)) {
            return parseRange(value)
        }
        if (isValidValue(value)) {
            return value
        }
        throw IllegalArgumentException("Unexpected input for ${humanReadableParserName()}: $value")
    }

    private fun allRangeValues(): String {
        var result = ""
        if (includeUpperBound) {
            for (i in lowerBoundRangeValue..upperBoundRangeValue) {
                result += "$i "
            }
        } else {
            for (i in lowerBoundRangeValue..<upperBoundRangeValue) {
                result += "$i "
            }
        }
        return result.trim()
    }

    private fun isValidValue(value: String): Boolean {
        val number = value.toInt()
        return number in 0..<upperBoundRangeValue
    }

    abstract fun humanReadableParserName(): String


    private fun parseRange(value: String): String {
        val splitted = value.split("-")
        val start = splitted[0].toInt()
        val end = splitted[1].toInt()
        var result = ""
        for (i in start..end) {
            result += "$i "
        }
        return result
    }

    private fun isRange(value: String): Boolean = value.contains("-")

    private fun isAll(value: String): Boolean = value == "*"

    private fun isAny(value: String): Boolean = value == "?"

    private fun parseIncremental(value: String, upperBound: Int = 60): String {
        val splitted = value.split("/")
        if (isAll(splitted[0])) {
            return "0 " + calculateNextElements(0, splitted[1].toInt(), upperBound)
        }
        return splitted[0] + " " + calculateNextElements(splitted[0].toInt(), splitted[1].toInt(), upperBound)
    }

    private fun calculateNextElements(start: Int, increment: Int, upperBound: Int): String {
        var nextElements = ""
        var sum = start
        while (sum + increment < upperBound) {
            sum += increment
            nextElements += "$sum "
        }
        return nextElements.trim()
    }

    private fun isIncrementalValue(value: String): Boolean = value.contains("/")
}