package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面，实现公共字段自动填充处理逻辑
 */
// @Aspect：表示该类是一个切面类，用于定义切面和通知。
// @Component：表示该类是一个由 Spring 管理的组件，会被自动扫描并注册为 Bean。
// @Slf4j：用于添加日志记录功能
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    // execution(* com.sky.mapper.*.*(..)) 是一个切入点表达式，用于定义切入点，即确定在哪些方法上应用切面
    // execution() 是切入点指示符的关键字，用于定义方法的执行。
    // * 表示匹配任意返回类型，可以是任何类型。
    // com.sky.mapper 是包名，表示要匹配的目标方法所在的包。
    // *.* 表示任意类和方法名。
    // (..) 表示匹配任意参数，可以是任意个数和类型的参数
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){

    }

    /**
     * 前置通知，在通知中进行公共字段的赋值
     */
    // @Before("autoFillPointCut()") 是一个前置通知，在目标方法执行之前执行
    // autoFill(JoinPoint joinPoint)：在切入点之前执行的前置通知。在这里，可以编写逻辑来实现公共字段的赋值操作
    // 在你的 autoFill() 方法中，JoinPoint joinPoint 是一个方法参数，它用于获取关于目标方法的信息。你可以使用 joinPoint 来获取方法的名称、参数、目标对象等信息，从而实现你的前置通知逻辑。
    //
    // 下面是一些常用的 JoinPoint 方法和属性：
    //
    // joinPoint.getSignature(): 获取方法签名，包括方法名和返回类型。
    // joinPoint.getArgs(): 获取方法的参数数组。
    // joinPoint.getTarget(): 获取目标对象，也就是被代理的对象。
    // joinPoint.getThis(): 获取代理对象，即代理类本身
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段自动填充...");

        // 获取到当前被拦截的方法上的数据库操作类型
        // joinPoint.getSignature() 返回的是 Signature 接口类型，该接口是所有方法签名信息的抽象。它包含了一些通用的方法签名信息，但不包含具体的方法注解。
        //
        //在这段代码中，我们需要获取方法上的 AutoFill 注解，而这个注解信息只能从 MethodSignature 类型的对象中获取。
        // 因此，需要将 Signature 接口类型的对象向下转型为 MethodSignature 类型，以便能够调用 getMethod() 方法获取方法对象，并通过该方法对象再获取方法上的注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();   // 方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);  // 获得方法上的注解对象
        OperationType operationType = autoFill.value();       // 获得数据库操作类型

        // 获取到当前被拦截的方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }

        Object entity = args[0];  // 因为可能有多个参数，此处规定实体类放在第一个参数的位置

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // 根据当前不同的操作类型，为对应的属性通过反射来赋值
        if(operationType == OperationType.INSERT){
            // 为4个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 通过反射为对象属性赋值
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType == OperationType.UPDATE){
            // 为2个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 通过反射为对象属性赋值
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
