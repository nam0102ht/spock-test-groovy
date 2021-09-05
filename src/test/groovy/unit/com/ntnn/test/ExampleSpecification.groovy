package unit.com.ntnn.test

import ntnn.Colour
import ntnn.Palette
import ntnn.Polygon
import ntnn.Renderer
import ntnn.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject

class ExampleSpecification extends Specification{
    def "should a simple to compare"() {
        expect:
            1 == 1
    }

    def "should demonstrate given-when-then"() {
        given:
            def polygon = new Polygon(4)
        when:
            int numberOfSides = polygon.numberOfSides
        then:
            numberOfSides == 4
    }

    def "should expect Exception to be  thrown for invalid input: #sides"() {
        when:
        new Polygon(sides)
        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides
        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides side"() {
        expect:
        new Polygon(sides).numberOfSides == sides
        where:
        sides << [3, 4, 5, 6, 7, 8, 9, 10]
    }

    def "should demonstrate data tables. Max of #a and #b should be #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "should be able to mock a concrete class"() {
        given:
        Renderer renderer = Mock()

        @Subject
        def shape = new Polygon(4, renderer)

        when:
        shape.draw()

        then:
        4 * renderer.drawLine()

    }


    def "should be able to use a stub with #colors"() {
        given:
        Palette palette = Stub()
        //palette will returns by assigning left of colors when configure Stub
        palette.getPrimaryColour() >> colors
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == colors

        where:
        colors << [Colour.RED, Colour.BLUE, Colour.YELLOW]
    }
}
