public class Product {
    private String name;        // 商品名称
    private double weight;      // 重量
    private double price;       // 价格
    private int accessoryCount; // 配件数量
    private String manufacturer; // 制造厂商

    public Product(String name, double weight, double price, int accessoryCount, String manufacturer) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.accessoryCount = accessoryCount;
        this.manufacturer = manufacturer;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "商品名称：" + name + "，重量：" + weight + "千克，价格：" + price + "元，配件数量：" + accessoryCount + "个，制造厂商：" + manufacturer;
    }

    // 重写equals方法，用于比较两个商品对象是否相同
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product otherProduct = (Product) obj;

        return name.equals(otherProduct.name) && weight == otherProduct.weight && price == otherProduct.price
                && accessoryCount == otherProduct.accessoryCount && manufacturer.equals(otherProduct.manufacturer);
    }

    public static void main(String[] args) {
        Product product1 = new Product("笔记本电脑", 2.5, 1000.0, 3, "戴尔");
        Product product2 = new Product("笔记本电脑", 2.5, 1000.0, 3, "戴尔");
        Product product3 = new Product("台式电脑", 10.0, 800.0, 2, "惠普");

        System.out.println("Product 1: " + product1);
        System.out.println("Product 2: " + product2);
        System.out.println("Product 3: " + product3);

        System.out.println("Product 1 是否等于 Product 2: " + product1.equals(product2));
        System.out.println("Product 1 是否等于 Product 3: " + product1.equals(product3));
    }
}
