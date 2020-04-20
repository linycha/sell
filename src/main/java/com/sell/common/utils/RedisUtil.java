package com.sell.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * redis工具类
 * 包含对String、Map、List、Set的操作
 * @author Cone
 *
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    //===============================common===============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间（秒）
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     * @param key 键，不能为空
     * @return 时间秒，返回0代表永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return 存在返回true，不存在返回false
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            System.out.println("aaaaa");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值，或多个值
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }
            else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //===============================String===============================
    @Component
    public class redisString {

        /**
         * 获取缓存
         * @param key 键
         * @return 值
         */
        public Object get(String key) {
            try {
                return key == null ? null : redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 缓存存入
         * @param key 键
         * @param value 值
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, Object value) {
            try {
                redisTemplate.opsForValue().set(key, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 普通缓存放入并设置时间
         * @param key 键
         * @param value 值
         * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, Object value, long time) {
            try {
                if (time > 0) {
                    redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                }
                else {
                    set(key, value);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 递增
         * @param key 键
         * @param delta 要增加的值
         * @return
         */
        public long incr(String key, long delta) {
            if (delta < 0) {
                throw new RuntimeException("递增因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, delta);
        }

        /**
         * 递减
         * @param key 键
         * @param delta 要减小的值
         * @return
         */
        public long decr(String key, long delta) {
            if (delta < 0) {
                throw new RuntimeException("递减因子必须小于0");
            }
            return redisTemplate.opsForValue().increment(key, -delta);
        }

    }

    //===============================Map===============================
    @Component
    public class redisMap {

        /**
         * 取得对应键值
         * @param key 键
         * @param item 项
         * @return 值
         */
        public Object get(String key, String item) {
            return redisTemplate.opsForHash().get(key, item);
        }

        /**
         * 获取hashKey对应的所有键值
         * @param key 键
         * @return map形式返回键值对
         */
        public Map<Object, Object> getAll(String key) {
            return redisTemplate.opsForHash().entries(key);
        }

        /**
         * 顾名思义，当然是set值啦
         * @param key 键
         * @param map 对应的多个键值
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, Map<String, Object> map) {
            try {
                redisTemplate.opsForHash().putAll(key, map);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 加强版set，可设置时间
         * @param key 键
         * @param map 对应的多个键值
         * @param time 缓存失效时间
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, Map<String, Object> map, long time) {
            try {
                redisTemplate.opsForHash().putAll(key, map);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 向一张hash表中放入数据,如果不存在将创建
         * @param key 键
         * @param item 项
         * @param value 值
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, String item, Object value) {
            try {
                redisTemplate.opsForHash().put(key, item, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 加强版set，可设置时间
         * @param key 键
         * @param item 项
         * @param value 值
         * @param time 缓存失效时间
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, String item, Object value, long time) {
            try {
                redisTemplate.opsForHash().put(key, item, value);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 删除hash表中的值
         * @param key 键，不能为空
         * @param item 项，不能为空，可以为多个
         */
        public void del(String key, Object... item) {
            redisTemplate.opsForHash().delete(key, item);
        }

        /**
         * 判断hash表中是否存在某值
         * @param key 键，不能为空
         * @param item 项，不能为空
         * @return 存在返回true，不存在返回false
         */
        public boolean hasKey(String key, String item) {
            return redisTemplate.opsForHash().hasKey(key, item);
        }

        /**
         * hash递减
         * @param key
         * @param item
         * @param by 要减少多少
         * @return
         */
        public double decr(String key, String item, double by) {
            return redisTemplate.opsForHash().increment(key, item, -by);
        }

        /**
         * hash递增
         * @param key
         * @param item
         * @param by 要增加多少
         * @return
         */
        public double incr(String key, String item, double by) {
            return redisTemplate.opsForHash().increment(key, item, by);
        }

    }

    //===============================Set===============================
    @Component
    public class redisSet {

        /**
         * 根据key获取Set中的所有值
         * @param key
         * @return
         */
        public Set<Object> get(String key) {
            try {
                return redisTemplate.opsForSet().members(key);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 根据value从一个set中查询,是否存在
         * @param key
         * @param value
         * @return 存在返回true，不存在返回false
         */
        public boolean hasKey(String key, Object value) {
            try {
                return redisTemplate.opsForSet().isMember(key, value);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 将数据放入set缓存
         * @param key
         * @param values
         * @return 成功个数
         */
        public long set(String key, Object... values) {
            try {
                return redisTemplate.opsForSet().add(key, values);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        /**
         * 将数据放入set缓存,可设置时间
         * @param key
         * @param time
         * @param values
         * @return 成功个数
         */
        public long set(String key, long time, Object... values) {
            try {
                Long count = redisTemplate.opsForSet().add(key, values);
                if (time > 0) {
                    expire(key, time);
                }
                return count;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        /**
         * 获取set缓存的长度
         * @param key
         * @return
         */
        public long getSize(String key) {
            try {
                return redisTemplate.opsForSet().size(key);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        /**
         * 移除set中值为value的项
         * @param key
         * @param values
         * @return 移除的个数
         */
        public long remove(String key, Object... values) {
            try {
                Long count = redisTemplate.opsForSet().remove(key, values);
                return count;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

    }


    //===============================List===============================
    @Component
    public class redisList {

        /**
         * 获取list缓存的内容
         * @param key
         * @param start
         * @param end 0到结束，-1代表所有值
         * @return
         */
        public List<Object> get(String key, long start, long end) {
            try {
                return redisTemplate.opsForList().range(key, start, end);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 获取list缓存的长度
         * @param key
         * @return
         */
        public long getSize(String key) {
            try {
                return redisTemplate.opsForList().size(key);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        /**
         * 通过索引 获取list中的值
         * @param key
         * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
         * @return
         */
        public Object getByIndex(String key, long index) {
            try {
                return redisTemplate.opsForList().index(key, index);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 将list放入缓存
         * @param key
         * @param value
         * @return
         */
        public boolean set(String key, Object value) {
            try {
                redisTemplate.opsForList().rightPush(key, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 将list放入缓存,可设置时间
         * @param key
         * @param value
         * @param time
         * @return
         */
        public boolean set(String key, Object value, long time) {
            try {
                redisTemplate.opsForList().rightPush(key, value);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 将list放入缓存
         * @param key
         * @param value
         * @return
         */
        public boolean set(String key, List<Object> value) {
            try {
                redisTemplate.opsForList().rightPushAll(key, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 将list放入缓存,可设置时间
         * @param key
         * @param value
         * @param time
         * @return
         */
        public boolean set(String key, List<Object> value, long time) {
            try {
                redisTemplate.opsForList().rightPushAll(key, value);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 根据索引修改list中的某条数据
         * @param key
         * @param index
         * @param value
         * @return
         */
        public boolean updateIndex(String key, long index, Object value) {
            try {
                redisTemplate.opsForList().set(key, index, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 删除list中值为value的项
         * @param key 键
         * @param count 要移除的个数
         * @param value
         * @return 移除的个数
         */
        public long remove(String key, long count, Object value) {
            try {
                Long remove = redisTemplate.opsForList().remove(key, count, value);
                return remove;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

    }


}
