import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.InvalidArgumentException
import com.xenomachina.argparser.default

class MyArgs(parser: ArgParser) {
    val version by parser.flagging(
        "-v", "--version",
        help = "shows the theoretical version"
    )

    val time by parser.flagging(
        "-t", "--time",
        help = "shows the current system time"
    )

    val author by parser.flagging(
        "-a", "--author",
        help = "prints the author's name"
    )

    val random: Array<Long>? by parser.storing(
        "-r", "--random",
        help = "rolls a random number between <MIN> and inclusive <MAX>. -> -r <MIN>,<MAX>",
    ) {
        val tokens = split(",")
        if (tokens.size != 2) {
            throw InvalidArgumentException("Must use only two Numbers! ($this)")
        }
        val n1 = tokens[0].toLong()
        val n2 = tokens[1].toLong()
        if (n1 > n2) {
            throw InvalidArgumentException("MIN is larger than MAX!")
        }
        arrayOf(n1, n2)
    }
        .default(null)

    val enum by parser.mapping(
        "--depression" to MyEnum.Depression,
        "--happiness" to MyEnum.Happiness,
        "--fatigue" to MyEnum.Fatigue,
        "--anxiety" to MyEnum.Anxiety,
        "--peckish" to MyEnum.Peckish,
        help = "Prints the selected Enums"
    ).default(null)

    val dryrun by parser.flagging(
        "-n", "--dry-run",
        help = "do nothing"
    )
}