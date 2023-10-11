import java.lang.Math;

public class Triangle {
    // 三边
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    public double calculateArea() {
        // 使用海伦公式计算三角形面积
        double s = (side1 + side2 + side3) / 2;
        double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        return area;
    }

    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    public static void main(String[] args) {
        // 测试数据
        Triangle triangle1 = new Triangle(3, 4, 5);
        System.out.println("三角形1的周长为：" + triangle1.calculatePerimeter());
        System.out.println("三角形1的面积为：" + triangle1.calculateArea());

        Triangle triangle2 = new Triangle(7, 8, 9);
        System.out.println("三角形2的周长为：" + triangle2.calculatePerimeter());
        System.out.println("三角形2的面积为：" + triangle2.calculateArea());
    }
}
