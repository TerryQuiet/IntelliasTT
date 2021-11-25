## ****Here is your test task and submission instructions:****

Write an app that will have a text input field where the user can enter an English word, and a search button after pressing which the app will search for the definition of this word.

For search use Free Dictionary API [https://dictionaryapi.dev/](https://click.mlflow.com/link/c/YT0xODI1NTM5MzIxNTQxNjk1Mjk4JmM9dDJzOCZlPTAmYj03OTczNDcwMjImZD1lNW8waTlr.WHVl1LmpoukYTgO2fTMvrDMlP_lBI7NyXc6otOIGyaQ), this one [https://api.dictionaryapi.dev/api/v2/entries/en/](https://click.mlflow.com/link/c/YT0xODI1NTM5MzIxNTQxNjk1Mjk4JmM9dDJzOCZlPTAmYj03OTczNDcwMjUmZD1oNXkyZzV1.BHBS9opRXL2hOzmJqH5bHRN-buylgdPKfPlIo78V2JU)

**Mandatory requirements:**

-   the app must show on UI list of mandatory fields from response JSON (for example look at the bottom), also feel free to add any other fields that you want to display, it will be a plus
-   the app must conform to Clean Architecture principles
-   on the presentation side use ViewModel

Here is the list of recommended libraries but you can use any library that you think will help you in solving this task:  

-   networking Retrofit
-   parsing of response GSON/Moshi
-   local cache Room/Realm

In terms of UI, there are no specific requirements, it should be sufficient to perform the required actions of searching for the word definition and showing the result. If you want, you can make it as fancy as you wish, it will be a plus.

**Nice to have:**

-   cache the result of all of the app requests in Local DB, and if a user searches for the word that was previously requested the response must be retrieved from local DB, without network request.
-   cover business login with UnitTests
-   for threading use coroutines  
    **[  
    Here you can see the example of mandatory fields to be displayed to the user](https://click.mlflow.com/link/c/YT0xODI1NTM5MzIxNTQxNjk1Mjk4JmM9dDJzOCZlPTAmYj04MDQ4OTEwMTQmZD1oOWozYjdx.TiQ0baUL5AsMR1iDZb1yuNZ1qojSTXN-bY8W9BDCnZM)**
