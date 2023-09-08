package com.example.java1234mailv3.entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义返回JSON 数据格式中日期格式化处理
 * @author java1234 小锋 老师
 *
 */
//这是一个自定义的 Jackson 序列化器类 CustomDateTimeSerializer，用于处理日期时间对象的 JSON 序列化过程。以下是该类的主要功能和结构：
//
//		CustomDateTimeSerializer 继承了 Jackson 中的 JsonSerializer<Date> 类，表示它是一个日期时间对象的自定义序列化器。
//
//		在 serialize 方法中，对日期时间对象进行格式化处理，并将格式化后的字符串写入 JSON 输出流中。
//
//		在 serialize 方法中，首先创建了一个 SimpleDateFormat 对象 sdf，用于指定日期时间的格式化规则。在这里，日期时间格式为 "yyyy-MM-dd HH:mm:ss"，并设置时区为 "Asia/Shanghai"。
//
//		然后，使用 gen.writeString() 方法将格式化后的日期时间字符串写入 JSON 输出流，以完成序列化。
//
//		总之，这个自定义序列化器的作用是将日期时间对象按照指定的格式转换为 JSON 字符串。在你的实体类中，
//		通过 @JsonSerialize(using=CustomDateTimeSerializer.class) 注解将该序列化器应用于日期时间属性，
//		确保它们以特定的格式在 JSON 中呈现
public class CustomDateTimeSerializer extends JsonSerializer<Date>{

	@Override
//	这三个参数分别代表了：
//	Date value：要序列化的日期时间对象，即需要将其转换为 JSON 格式的对象。
//
//	JsonGenerator gen：JSON 生成器，它允许你将 JSON 写入输出流中。在 serialize 方法中，你可以使用 gen 对象的方法来写入 JSON 数据。
//
//	SerializerProvider serializers：序列化提供程序，它包含有关序列化过程的各种配置信息和上下文信息。
//	你可以使用 serializers 对象来获取有关序列化环境的信息，以便根据需要进行进一步的自定义序列化。
//
//	这三个参数一起协作，使得在 serialize 方法内部可以将 value（日期时间对象）以特定格式写入 JSON 输出流 gen 中，
//	而 serializers 则提供了有关序列化上下文的额外信息，供你在自定义序列化过程中使用
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		gen.writeString(sdf.format(value));  
	}

}
