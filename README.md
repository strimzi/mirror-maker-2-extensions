[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Twitter Follow](https://img.shields.io/twitter/follow/strimziio.svg?style=social&label=Follow&style=for-the-badge)](https://twitter.com/strimziio)

# Mirror Maker 2 Extensions

This repository is an extensions library for use with Mirror Maker 2.
It allows for example usage of custom replication policies.

## Identity Replication Policy

**Note: From Kafka 3.0.0, Apache Kafka now has its own _Identity Replication Policy_: `org.apache.kafka.connect.mirror.IdentityReplicationPolicy`.
When using Kafka 3.0.0 and newer, you should use the Apache Kafka policy instead of the Strimzi policy which will be archived in the future.**

MirrorMaker v2 (MM2), which ships as part of Apache Kafka in version 2.4.0 and above, detects and 
replicates topics, topic partitions, topic configurations and topic ACLs to the destination cluster that matches a regex topic pattern. 
Further, it checks for new topics that matches the topic pattern or changes to configurations and ACLs at regular configurable intervals. 
The topic pattern can also be dynamically changed by changing the configuration of the MirrorSourceConnector. 
Therefore MM2 can be used to migrate topics and topic data to the destination cluster and keep them in sync.
                   
In order to differentiate topics between the source and destination, MM2 utilizes a **ReplicationPolicy**. 
The **DefaultReplicationPolicy** implementation uses a **\<source-cluster-alias\>.\<topic\>** naming convention as described 
in [KIP-382](https://cwiki.apachorg/confluence/display/KAFKA/KIP-382%3A+MirrorMaker+2.0#KIP-382:MirrorMaker2.0-RemoteTopics,Partitions).The consumer, 
when it starts up will subscribe to the replicated topic based on the topic pattern specified which should account for 
both the source topic and the replicated topic names. This behavior is designed to account for use cases which need to run multiple 
Apache Kafka clusters and keep them in sync for High Availability/Disaster Recovery and prevent circular replication of topics.

In migration or active-passive DRP scenarios, it might be useful to have the same topic names in the destination as the source as there is no 
failback requirement and the replication is only way from the source to target Apache Kafka cluster. 
In order to enable that, the **DefaultReplicationPolicy** needs to be replaced with an **IdentityReplicationPolicy** which would 
maintain the same topic name at the destination. This jar file needs to be copied into the **libs** directory of the 
Apache Kafka installation running MM2.

Note: inspirations were taken from [here](https://github.com/aws-samples/mirrormaker2-msk-migration).
