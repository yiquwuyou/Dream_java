// 抽象父类 Car
abstract class Car {
    protected int maxSpeed; // 最大速度
    protected int exceed; // 中速度
    protected int speed; // 速度

    public Car(int maxSpeed, int exceed, int speed) {
        this.maxSpeed = maxSpeed;
        this.exceed = exceed;
        this.speed = speed;
    }

    // 抽象方法 stop()
    public abstract void stop();
}

// 子类 Ford
class Ford extends Car {
    public Ford(int maxSpeed, int exceed, int speed) {
        super(maxSpeed, exceed, speed);
    }

    @Override
    public void stop() {
        if (speed > maxSpeed) {
            System.out.println("Ford车速超过" + maxSpeed + "公里/小时，显示停车");
        } else if (speed > exceed) {
            System.out.println("Ford车速超过" + exceed + "公里/小时，提示超速，扣1分积分，罚款200元");
        } else {
            System.out.println("Ford正常行驶");
        }
    }
}

// 子类 QQ
class QQ extends Car {
    public QQ(int maxSpeed, int exceed, int speed) {
        super(maxSpeed, exceed, speed);
    }

    @Override
    public void stop() {
        if (speed > maxSpeed) {
            System.out.println("QQ车速超过" + maxSpeed + "公里/小时，显示停车");
        } else if (speed > exceed) {
            System.out.println("QQ车速超过" + exceed + "公里/小时，提示超速，扣1分积分，罚款200元");
        } else {
            System.out.println("QQ正常行驶");
        }
    }
}

// 测试类
public class CarTest {
    public static void main(String[] args) {
        Car fordCar = new Ford(200, 150, 180);
        Car qqCar1 = new QQ(150, 120, 160);
        Car qqCar2= new QQ(150, 50, 160);

        System.out.println("Ford车状态：");
        fordCar.stop();

        System.out.println("\nQQ车状态1：");
        qqCar1.stop();

        System.out.println("\nQQ车状态2：");
        qqCar1.stop();
    }
}
