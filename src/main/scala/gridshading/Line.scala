package gridshading

object Line 
{
    def apply( length : Int, hints : Seq[Int] ) : Line =
    {
        new Line( length, hints, Seq() )
    }
}

class Line protected [gridshading] (
    protected [gridshading] val length : Int,
    protected [gridshading] val hints : Seq[Int],
    protected [gridshading] val shaded : Seq[Int]
)
{
    if (length < 1) throw new InvalidLineException("Line must have positive length")

    protected [gridshading] def shadedGroups() : Seq[Int] =
    {
        shaded.foldLeft(Seq[Int](), None : Option[Int]){case ((groups, previousOption), v) => {
            previousOption match {
                case None => (Seq(1), Some(v))
                case Some(previous) => v - previous == 1 match {
                    case true => (groups.init :+ groups.last + 1, Some(v))
                    case false => (groups :+ 1, Some(v))
                }
            }
        }}._1
    }

    def complete() : Boolean =
    {
        shadedGroups == hints
    }

    def shade(index : Int) : Line =
    {
        if (!shaded.contains(index)) new Line(length, hints, shaded :+ index) else this
    }

    override def hashCode : Int = length.hashCode + hints.hashCode() + shaded.hashCode()

    override def equals(any : Any) : Boolean = any match
    {
        case other : Line => length == other.length && hints == other.hints && shaded == other.shaded
        case _ => false
    }
}

class InvalidLineException(msg : String) extends Exception