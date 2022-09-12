package com.qihaocai.myapplication

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {

    var wordToGuess = ""
    var returnedresult = ""
    var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wordInput = findViewById<EditText>(R.id.wordInput)


        wordToGuess = (FourLetterWordList.getRandomFourLetterWord()).lowercase()
        Log.d(TAG, "onCreate: " + wordToGuess)

        //TextViews
        val Guess1T = findViewById<TextView>(R.id.Guess1textView)
        val Guess1CT = findViewById<TextView>(R.id.Guess1CtextView)
        val Guess2T = findViewById<TextView>(R.id.Guess2textView)
        val Guess2CT = findViewById<TextView>(R.id.Guess2CtextView)
        val Guess3T = findViewById<TextView>(R.id.Guess3textView)
        val Guess3CT = findViewById<TextView>(R.id.Guess3CtextView)
        val win = findViewById<TextView>(R.id.win)

        win.isInvisible = true
        Guess1CT.isInvisible = true
        Guess2T.isInvisible = true
        Guess2CT.isInvisible = true
        Guess3T.isInvisible = true
        Guess3CT.isInvisible = true


        var Guess1 = findViewById<TextView>(R.id.Guess_1)
        var Guess1Check = findViewById<TextView>(R.id.Guess_1_Check)
        var Guess2 = findViewById<TextView>(R.id.Guess_2)
        var Guess2Check = findViewById<TextView>(R.id.Guess_2_Check)
        var Guess3 = findViewById<TextView>(R.id.Guess_3)
        var Guess3Check = findViewById<TextView>(R.id.Guess_3_Check)

        var Result = findViewById<TextView>(R.id.Result)


        //button checks guess.
        val button = findViewById<Button>(R.id.button)
        val reset = findViewById<Button>(R.id.reset)
        reset.visibility = View.INVISIBLE



        button.setOnClickListener {

            val hidekeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            hidekeyboard.hideSoftInputFromWindow(button.getWindowToken(), 0)

            var wordInputted = (wordInput.text.toString()).lowercase()
            Log.d(TAG, "word inputted: " + wordInputted)

            returnedresult = checkGuess(wordInputted)


            if (counter == 0) {
                Guess1T.isInvisible = false
                Guess1CT.isInvisible = false
                Guess1.text = wordInput.text
                Guess1Check.text = returnedresult
                wordInput.setText("")
            }

            if (counter == 1) {
                Guess2T.isInvisible = false
                Guess2CT.isInvisible = false
                Guess2.text = wordInput.text
                Guess2Check.text = returnedresult
                wordInput.setText("")
            }

            if (counter == 2) {
                Guess3T.isInvisible = false
                Guess3CT.isInvisible = false
                Guess3.text = wordInput.text
                Guess3Check.text = returnedresult
                wordInput.setText("")

            }

            counter++

            if(returnedresult == "OOOO"){
                win.isInvisible = false
                counter = 6
            }

            if(counter == 3 || counter == 6){
                button.visibility = View.INVISIBLE
                reset.visibility = View.VISIBLE
                Result.text = wordToGuess
            }
            //wordInputted = ""
        }

        reset.setOnClickListener {
            reset.visibility = View.INVISIBLE
            button.visibility = View.VISIBLE

            Guess1CT.isInvisible = true
            Guess2T.isInvisible = true
            Guess2CT.isInvisible = true
            Guess3T.isInvisible = true
            Guess3CT.isInvisible = true
            win.isInvisible = true

            Guess1.text = ""
            Guess1Check.text = ""

            Guess2.text = ""
            Guess2Check.text = ""

            Guess3.text = ""
            Guess3Check.text = ""

            Result.text = ""

            counter = 0
        }

    }

    object FourLetterWordList {
        // List of most common 4 letter words from: https://7esl.com/4-letter-words/
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club," +
                    "Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food," +
                    "Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea," +
                    "Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name," +
                    "Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road," +
                    "Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time," +
                    "Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn," +
                    "Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang," +
                    "Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make," +
                    "Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save," +
                    "Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want," +
                    "Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast," +
                    "Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male," +
                    "Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore," +
                    "Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever," +
                    "Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

        // Returns a list of four letter words as a list
        fun getAllFourLetterWords(): List<String> {
            return fourLetterWords.split(",")
        }

        // Returns a random four letter word from the list in all caps
        fun getRandomFourLetterWord(): String {
            val allWords = getAllFourLetterWords()
            val randomNumber = (0..allWords.size).shuffled().last()
            return allWords[randomNumber].uppercase()
        }
    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}