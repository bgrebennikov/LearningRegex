import EXPHelper.parseWords
import java.util.regex.Pattern

fun main(args: Array<String>) {
    println(
        parseWords(
            "Hello, my name is Boris, i live in #Samara "+
                    "you can contact me via email: bgrebennikovv@gmail.com "+
                    "Or find me on Telegram: https://t.me/fck_st"+
                    "Thanks!"
        )
    )
}

