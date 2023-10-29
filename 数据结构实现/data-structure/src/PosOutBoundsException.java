
// 自定义异常类
public class PosOutBoundsException extends RuntimeException{
    public PosOutBoundsException(){

    }

    public PosOutBoundsException(String message){
        super(message);
    }
}
