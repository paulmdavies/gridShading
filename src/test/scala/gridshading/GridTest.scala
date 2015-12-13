package gridshading

import org.scalatest._

class GridTest extends FeatureSpec with GivenWhenThen
{
    info("A Grid should represent the status of a complete shading grid")

    feature("Construction")
    {
        scenario("A Grid should have the correct number of rows and columns")
        {
            Given("A grid constructed with 3 sets of column hints and two sets of row hints")
            val grid = Grid(Seq(Seq(1), Seq(1), Seq(2)), Seq(Seq(1, 1), Seq(2)))

            Then("The grid should have 3 columns")
            assert(grid.columns == 3)

            And("The grid should have two rows")
            assert(grid.rows == 2)
        }

        scenario("A Grid with no columns shouldn't be allowed")
        {
            Given("No columns")
            val columns = Seq()

            And("2 rows")
            val rows = Seq(Seq(1), Seq(2))

            Then("Constructing a grid should throw an exception")
            intercept[InvalidGridException] {
                Grid(columns, rows)
            }
        }

        scenario("A Grid with no rows shouldn't be allowed")
        {
            Given("2 columns")
            val columns = Seq(Seq(1), Seq(2))

            And("No rows")
            val rows = Seq()

            Then("Constructing a grid should throw an exception")
            intercept[InvalidGridException] {
                Grid(columns, rows)
            }
        }
    }
}
