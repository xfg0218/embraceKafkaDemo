公司kafka测试环境

1、测试及器
192.168.3.210  用户名/密码:root/redhat

2、访问地址
http://192.168.3.210:8080   用户名/密码:admin/admin

3、kafka官网
http://kafka.apache.org/0102/documentation/streams/


4、kafka命令行查看
cd /usr/hdp/2.6.0.3-8/kafka/bin

查看有多少topic
./kafka-topics.sh --list --zookeeper 192.168.3.210:2181

创建topic
./kafka-topics.sh --create --zookeeper 192.168.3.210:2181 --replication-factor 1 --partitions 3 --topic xiaoxu
Created topic "xiaoxu".

参数说明:--replication-factor 1  副本数，就是这个分片复制1分
        --partitions 3  多少个分片数
        -- xiaoxu  topic的名字

删除topic
./kafka-topics.sh --delete --zookeeper 192.168.3.210:2181  --topic xiaoxu-test1
Topic xiaoxu-test1 is marked for deletion.
Note: This will have no impact if delete.topic.enable is not set to true.


./kafka-topics.sh --list --zookeeper 192.168.3.210:2181
*******
xiaoxu-test1 - marked for deletion

可以看出已经标记为删除了


shell 生产消息
./kafka-console-producer.sh --broker-list 192.168.3.210:6667 --topic xiaoxu1    
ss
xiaoxu


shell消费消息
./kafka-console-consumer.sh --zookeeper 192.168.3.210:2181 --from-beginning --topic xiaoxu1
Using the ConsoleConsumer with old consumer is deprecated and will be removed in a future major release. Consider using the new consumer by passing [bootstrap-server] instead of [zookeeper].
{metadata.broker.list=alpha-cn-01.cars.com:6667, request.timeout.ms=30000, client.id=console-consumer-32722, security.protocol=PLAINTEXT}
ss
xiaoxu


4、web查看消费情况
http://192.168.3.210:9092


5、kafka 官网
http://kafka.apache.org/0102/documentation/streams/
