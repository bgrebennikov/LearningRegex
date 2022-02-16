import java.util.regex.Pattern

object EXPHelper {

    private const val HASHTAG_EXP = "(#[A-Za-z0-9-_]+)(?:#[A-Za-z0-9-_]+)*"
    private const val URL_EXP = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
    private const val EMAIL_EXP = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"

    val expressionsList = HashMap<WordType, String>().apply {
        put(WordType.HASHTAG, HASHTAG_EXP)
        put(WordType.EMAIL, EMAIL_EXP)
        put(WordType.URL, URL_EXP)
    }

    val allExpressions: String = expressionsList.values.joinToString("|")

    fun parseWords(text: String) : List<WordModel>{

        val result = mutableListOf<WordModel>()
        val pattern = Pattern.compile(EXPHelper.allExpressions)

        val matcher = pattern.matcher(text)

        while (matcher.find()){
            val wordsPosition = listOf<Int>(matcher.start(0), matcher.end())
            val executedWord = text.substring(wordsPosition[0], wordsPosition[1])

            result.add(WordModel(
                word = executedWord,
                startIndex = matcher.start(0),
                endIndex = matcher.end(),
                type = getType(executedWord)
            ))
        }

        return result

    }

    private fun getType(word: String) : WordType{
        var pattern: Pattern
        for (type in EXPHelper.expressionsList){
            pattern = Pattern.compile(type.value)
            if(pattern.matcher(word).find()) return type.key
        }

        return WordType.UNKNOWN

    }

}
