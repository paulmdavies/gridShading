package gridshading

object Grid
{
    def apply( columnHints : Seq[Seq[Int]], rowHints : Seq[Seq[Int]] ) : Grid =
    {
        new Grid(columnHints, rowHints)
    }

}

class Grid protected [gridshading] (
    protected [gridshading] val columnHints : Seq[Seq[Int]],
    protected [gridshading] val rowHints : Seq[Seq[Int]]
)
{
    protected [gridshading] val columns = columnHints.length
    protected [gridshading] val rows = rowHints.length

    if ( rows == 0 || columns == 0 ) throw new InvalidGridException("Invalid grid: must have at least one row and column")
}

class InvalidGridException( msg : String ) extends Exception(msg)
