# UserManagementSystem
Eureka Server, Rest API, Kafka

step 1:
run eureka server, userservice,userregistration...

step 2: type it in browser...
http://localhost:8761/

step 3: type it in browser...
http://localhost:8080/h2-console
 -> connect to database
 -> run the command -- "SELECT * FROM USER_DETAILS ;" -- to check the entries in the table
step 4: open postman..
type...
 -> POST > http://localhost:8080
 {
    "username":"Ayesha",
    "email" : "ayesha@67.com",
    "address" : {
        "city" : "guntur",
        "state" : "Andhra pradesh"
    }
}

Have some more records..
step 4: type..
 -> GET> http://localhost:8081/1
 to see the record of id=1..
step 5: stop running the userservice..
step 6: again in postman....type..
 -> GET> http://localhost:8081

 And see the results..
 you try using kafka...

 1. To start zookeeper:
zookeeper-server-start.bat  ../../config/zookeeper.properties
zookeeper-server-stop.bat  ../../config/zookeeper.properties

2. To start Kafka
kafka-server-start.bat  ../../config/server.properties
kafka-server-stop.bat  ../../config/server.properties

3. Create a topic with 2 partitions
kafka-topics.bat --bootstrap-server localhost:9092 --create --topic usermanagement-topic --partitions 2

Describe a topic
kafka-topics --bootstrap-server localhost:9092 --describe --topic usermanagement-topic

4. Kafka console producer
kafka-console-producer.bat --bootstrap-server localhost:9092 --topic  usermanagement-topic

5. Kafka console consumer
kafka-console-consumer.bat --bootstrap-server localhost:9092  --topic usermanagement-topic --from-beginning

6. To get a detailed description about the log file, use a tool kafka-run-class
kafka-run-class.bat kafka.tools.DumpLogSegments --deep-iteration --print-data-log --files C:/logs/kafka-logs/usermanagement-topic-0/00000000000000000000.log

7.To get End offset details for each partition
kafka-run-class.bat kafka.tools.GetOffsetShell --bootstrap-server localhost:9092 --topic usermanagement-topic

8. Check offset for given timestamp
kafka-run-class.bat kafka.tools.GetOffsetShell --bootstrap-server localhost:9092 --topic usermanagement-topic --time 1572780669989

9. Retrieve one message at a time from specific offset and partition:
kafka-console-consumer.bat --bootstrap-server localhost:9092 --max-messages 1  --partition 0  --offset 3 --topic usermanagement-topic


10. kafka send they records with key and value
kafka-console-producer.bat --bootstrap-server localhost:9092 --topic test --property "parse.key=true" --property "key.separator=:"
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --property print.key=true --property key.separator=":" --from-beginning
