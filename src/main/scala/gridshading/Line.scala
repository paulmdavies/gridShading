package gridshading

object Line 
{
    def apply( length : Int, locations : Seq[Int] ) : Line = 
    {
        new Line( length, locations, Seq() )
    }
}

class Line protected [gridshading] ( val length : Int, val locations : Seq[Int], val shaded : Seq[Int] )
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

    def validate() : Boolean =
    {
        shadedGroups == locations
    }
    
    def complete() : Boolean =
    {
        shaded.length == locations.sum && validate
    }
}