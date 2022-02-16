# Parse links from text via RegEx
### Supported types:
- Hashtags
- Urls
- emails

### Using in project:
```
import EXPHelper.parseWords


fun main(args: Array<String>) {
    val data = parseWords(
        "Hello, my name is Boris, i live in #Samara "+
                "you can contact me via email: bgrebennikovv@gmail.com "+
                "Or find me on Telegram: https://t.me/fck_st"+
                "Thanks!"
    )

    // It returns list of all parsed elements like urls, emails and hashtags
    println("Any: $data \n")

    // The WordModel contains a some information, for example you get data with filter
    // This example returns only hashtags
    println("Only hashtags: ${data.filter { it.type == WordType.HASHTAG }}")

}
```

### Output:
```
Any: [
    WordModel(
        word=#Samara, 
        startIndex=35, 
        endIndex=42, 
        type=HASHTAG),
    WordModel(
        word=bgrebennikovv@gmail.com, 
        startIndex=73, 
        endIndex=96, 
        type=EMAIL), 
    WordModel(
        word=https://t.me/fck_stThanks, 
        startIndex=121, 
        endIndex=146, 
        type=URL)
    ] 

Only hashtags: [
    WordModel(
    word=#Samara, 
    startIndex=35, 
    endIndex=42, 
    type=HASHTAG)
    ]
```
> "startIndex" and "endIndex" means position of chars in original text.


### Which types we can use:
___Currently it has only three types: Hashtags, Emails, and Urls___
