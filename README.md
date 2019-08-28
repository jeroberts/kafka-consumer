# Kafka Consumer using Spring Boot
The purpose of this repo is to help me learn how to create and use a simple Kafka Consumer.  Currently only supported as a single Consumer.  I will look into using this in a Consumer Group in the future.

## Running Consumer
These instructions assume ~/kafka
### Start Zookeeper  
* ```~/kafka/bin/zookeeper-server-start.sh ~/kafka/config/zookeeper.properties```

### Start Kafka  
* ```~/kafka/bin/kafka-server-start.sh ~/kafka/config/server.properties```

### Create Kafka Topic
Person_Topic will be used for this example.
* ```~/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Person_Topic```

### Run Spring Boot Consumer locally
* ```cd kafka-consumer```  
```mvn spring-boot:run```  

### Run Spring Boot Producer locally
* [Link to kafka-producer repo](https://github.com/jeroberts/kafka-producer) 
* ```cd kafka-producer```  
```mvn spring-boot:run```  
* POST data locally through REST endpoint.