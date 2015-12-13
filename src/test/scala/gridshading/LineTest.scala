package gridshading

import org.scalatest._

class LineTest extends FeatureSpec with GivenWhenThen
{
    info("A line should work as required")

    feature("Construction")
    {
        scenario("A line of length zero shouldn't be allowed")
        {
            Given("A length of zero")
            val length = 0

            Then("Constructing a Line should result in an error")
            intercept[InvalidLineException] {
                Line(length, Seq())
            }
        }
    }

    feature("Completeness")
    {
        scenario("Validation fails if a line is incorrectly shaded")
        {
            Given("A line of length 3 has groups [1, 1] and shaded [0,1]")
            val line = new Line(3, Seq(1, 1), Seq(0, 1))

            Then("The line should have shaded groups of [2]")
            assert(line.shadedGroups() == Seq(2))

            And("Completeness should fail")
            assert(!line.complete())
        }

        scenario("Validation fails if a line has no shading")
        {
            Given("A line of length 3 has groups [1, 1] and shaded []")
            val line = new Line(3, Seq(1, 1), Seq())

            Then("The line should have shaded groups of []")
            assert(line.shadedGroups() == Seq())

            And("Completeness should fail")
            assert(!line.complete())
        }

        scenario("Validation passes if a line has a valid possible shading")
        {
            Given("A line of length 3 has groups [1, 1] and shaded [0, 2]")
            val line = new Line(3, Seq(1, 1), Seq(0, 2))

            Then("The line should have shaded groups of [1,1]")
            assert(line.shadedGroups() == Seq(1, 1))

            And("Completeness should fail")
            assert(line.complete())
        }
    }

    feature("Shading")
    {
        scenario("Shading a blank square should result in it being shaded")
        {
            Given("A line with no shading")
            var line = Line(3, Seq(1))

            When("Shading a square")
            line = line.shade(1)

            Then("The square should be shaded")
            assert(line.shaded == Seq(1))
        }

        scenario("Shading a shaded square should have no effect")
        {
            Given("A line with square 1 shaded")
            var line = new Line(3, Seq(1), Seq(1))

            When("Shading square 1")
            val newLine = line.shade(1)

            Then("No change should result")
            assert(line == newLine)
        }
    }
}