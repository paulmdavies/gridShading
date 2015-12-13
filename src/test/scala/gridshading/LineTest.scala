package gridshading

import org.scalatest._

class LineTest extends FeatureSpec with GivenWhenThen
{
    info("A line should work as required")
    
    feature("Validation")
    {
        scenario("Validation fails if a line is incorrectly shaded") {
            Given("A line of length 3 has groups [1, 1] and shaded [0,1]")
            val line = new Line(3, Seq(1, 1), Seq(0, 1))
            
            Then("The line should have shaded groups of [2]")
            assert(line.shadedGroups() == Seq(2))

            And("Validation should fail")
            assert(!line.validate())
        }
        
        scenario("Validation fails if a line has no shading") {
            Given("A line of length 3 has groups [1, 1] and shaded []")
            val line = new Line(3, Seq(1, 1), Seq())

            Then("The line should have shaded groups of []")
            assert(line.shadedGroups() == Seq())

            And("Validation should fail")
            assert(!line.validate())
        }
        
        scenario("Validation passes if a line has a valid possible shading") {
            Given("A line of length 3 has groups [1, 1] and shaded [0, 2]")
            val line = new Line(3, Seq(1, 1), Seq(0, 2))

            Then("The line should have shaded groups of [1,1]")
            assert(line.shadedGroups() == Seq(1,1))

            And("Validation should fail")
            assert(line.validate())
        }
    }
    
    feature("Completeness") 
    {
    }
}