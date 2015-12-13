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
    protected val toBeShaded = length - locations.sum
    protected val toBeBlank = length - toBeShaded

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
}