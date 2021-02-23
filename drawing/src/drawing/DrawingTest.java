package drawing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class Point {
	int x;
	int y;
	
	Point(int x, int y) { this.x = x; this.y = y; }
}

abstract class Shape extends Object {}

// Circle is a subclass of Shape
// Shape is the superclass of Circle
// Circle extends Shape
// Shape generalizes Circle and Polygon
// Circle is a subtype of Shape
// Circle inherits from Shape
// Shape inherits from Object

class Circle extends Shape {
	Point center;
	int radius;
	
	Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
}

class Polygon extends Shape {
	Point[] vertices;
	
	Polygon(Point[] vertices) {
		this.vertices = vertices;
	}
}

class Drawing {
	Shape[] shapes;
	
	Drawing(Shape[] shapes) {
		this.shapes = shapes;
	}
	
	String toText() {
		String result = "";
		for (int i = 0; i < shapes.length; i++) {
			Shape shape = shapes[i]; // polymorphic variable/polymorfe variabele
			if (shape instanceof Circle) {
				// static type checker
				// incomplete
				// typecast
				Circle circle = (Circle)shape; // ClassCastException
				result += "circle: " + circle.center.x + " " + circle.center.y + " " + circle.radius + " ";
			} else {
				assert shape instanceof Polygon;
				Polygon polygon = (Polygon)shape;
				result += "polygon: " + Arrays.stream(polygon.vertices).map(p -> p.x + " " + p.y).collect(Collectors.joining(" ")) + " ";
			}
		}
		return result;
	}
}

class DrawingTest {

	@Test
	void test() {
		Drawing drawing = new Drawing(new Shape[] {
			new Circle(new Point(0, 0), 5),
			new Polygon(new Point[] {new Point(1, 0), new Point(0, 1), new Point(1, 1)}),
			new Circle(new Point(10, 10), 5)
		});
		assertEquals("circle: 0 0 5 polygon: 1 0 0 1 1 1 circle: 10 10 5 ", drawing.toText());
		
		int x = 10;
		//Object object = Integer.valueOf(10); // Wrapper class Integer
		Object object = 10;
		
		assertTrue(object instanceof Integer);
		Integer y = (Integer)object;
		assertTrue(y.intValue() == 10);
		
		int z = (int)object;
		// int z = ((Integer)object).intValue();
	}

}
