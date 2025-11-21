import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.DefaultHelpFormatter
import com.xenomachina.argparser.HelpFormatter
import com.xenomachina.argparser.ShowHelpException
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextLong

fun main(args: Array<String>) {
    try {
        val myargs = ArgParser(args).parseInto(::MyArgs)
        myargs.run {
            if (dryrun) {
                println("yea i guess your device is turned on")
                return
            }
            val tokens = ArrayList<String>()
            if (version) tokens.add("VERSION 1.0")
            if (author) tokens.add("by LionTion")
            enum?.let { tokens.add("feeling " + it.name) }
            val fancytext = StringBuilder()
            for (token in tokens) {
                if (fancytext.isNotEmpty()) {
                    fancytext.append(" ").append(token)
                } else {
                    fancytext.append(token.first().uppercase() + token.drop(1))
                }
            }
            if (fancytext.isNotEmpty()) println(fancytext.toString())
            if (time) {
                println("Current System Time is " + SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date()))
            }
            random?.let {
                val min = it[0]
                val max = it[1]
                println("Rolled a " + Random.nextLong(min..max) + "!")
            }
        }
    } catch (e: ShowHelpException) {
        e.printAndExit("JarAndArgs")
    }
}