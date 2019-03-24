package com.lls.seckill.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/************************************
 * SerializerUtils
 * @author liliangshan
 * @date 2019-03-23
 ************************************/
public class SerializerUtils {

  // 缓存schema对象的map 但 protostuff 内部已做缓存考虑  所以cachedSchema无用了
  private static final Map<Class<?>, RuntimeSchema<?>> cachedSchema = new ConcurrentHashMap<>();

  @SuppressWarnings({"unchecked", "unused"})
  private static <T> RuntimeSchema<T> getSchema(Class<T> clz) {
    // 先尝试从缓存schema map中获取相应类型的schema
    RuntimeSchema<T> schema = (RuntimeSchema<T>) cachedSchema.get(clz);
    if (schema == null) {
      schema = RuntimeSchema.createFrom(clz);
      cachedSchema.put(clz, schema);
    }
    return schema;
  }

  @SuppressWarnings({"unused"})
  private static <T> RuntimeSchema<T> getFrameworkCachedSchema(Class<T> clz) {
    return (RuntimeSchema<T>) RuntimeSchema.getSchema(clz); // protostuff 内部已做缓存考虑  所以上述的方法无用了
  }

  @SuppressWarnings({"unchecked"})
  public static <T> byte[] serialize(T object) {
    // 获取泛型对象的类型
    Class<T> clz = (Class<T>) object.getClass();
    // 创建泛型对象的schema对象
    RuntimeSchema<T> schema = getFrameworkCachedSchema(clz);
    // 创建LinkedBuffer对象
    LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    return ProtobufIOUtil.toByteArray(object, schema, buffer);
  }

  public static <T> T deserialize(byte[] bytes, Class<T> clz) {
    // 创建泛型对象的schema对象
    RuntimeSchema<T> schema = getFrameworkCachedSchema(clz);
    // 根据schema实例化对象
    T message = schema.newMessage(); // 这里不要用对象自己的 newInstance 会有坑 用工具提供的message
    // 将字节数组中的数据反序列化到message对象
    ProtobufIOUtil.mergeFrom(bytes, message, schema);
    return message;
  }

}
