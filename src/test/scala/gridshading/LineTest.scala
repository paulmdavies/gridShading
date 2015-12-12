package gridshading

import org.scalatest._

class LineTest extends FeatureSpec with GivenWhenThen
{
    info("A line should work as required")
    
    feature("Validation")
    {
        scenario("Validation fails if line is incorrectly shaded") {
            Given("A line of length 3 has groups [1, 1] and shaded [0,1]")
            val line = new Line(3, Seq(1, 1), Seq(0, 1))
            
            When("The line is validated")
            val valid = line.validate()
            
            Then("Validation fails")
            assert(!valid)
        }
        
        scenario("Validation fails if line has no shading") {
            Given("A line of length 3 has groups [1, 1] and shaded []")
            val line = new Line(3, Seq(1, 1), Seq())
            
            When("The line is validated")
            val valid = line.validate()
            
            Then("Validation fails")
            assert(!valid)
        }
    }
    
    feature("Completeness") 
    {
    }
}