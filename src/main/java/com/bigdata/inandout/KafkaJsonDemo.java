package com.bigdata.inandout;

import com.bigdata.bean.CycleTagBean;
import com.bigdata.serializer.HmCycleTagSchema;
import lombok.val;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

/**
 * @Auther wangtan
 * @Date 2020/10/16
 */
public class KafkaJsonDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        val properties=new Properties();
        //这两个的properties是必须传递的
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "consumer01");
        DataStream<CycleTagBean> stream = env
                .addSource(new FlinkKafkaConsumer010<>("test",
                        new HmCycleTagSchema(),
                        properties));
        stream.print();
        env.execute("kafka Json input");
    }
}