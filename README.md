[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Twitter Follow](https://img.shields.io/twitter/follow/strimziio.svg?style=social&label=Follow&style=for-the-badge)](https://twitter.com/strimziio)

# MirrorMaker 2 Extensions

## Identity Replication Policy

Replication policies in Apache Kafka MirrorMaker 2 define how will the mirrored topics be named on the target cluster.
The default replication policy prefixes the topic names with the name of the source cluster.
Users can provide and configure their own replication policies.

The identity replication policy provided by Strimzi keeps the topic names on the target cluster the same as they are on the source cluster.  
The policy is implemented in class `io.strimzi.kafka.connect.mirror.IdentityReplicationPolicy`.

**From Kafka 3.0.0, Apache Kafka now has its own _Identity Replication Policy_: `org.apache.kafka.connect.mirror.IdentityReplicationPolicy`.
When using Kafka 3.0.0 and newer, you should use the Apache Kafka policy instead of the Strimzi policy which is deprecated and will be archived in the future.**

From the version 1.2.0, the Strimzi identity replication policy provides exactly the same functionality as Kafka's own policy and works only with Kafka 3.0.0 and newer..
It is provided only for backwards compatibility reasons for Strimzi users with MirrorMaker 2 clusters configured to use `io.strimzi.kafka.connect.mirror.IdentityReplicationPolicy`.

**If you want to use the identity replication policy with Apache Kafka 2.8 or older, please use the version [1.1.0](https://mvnrepository.com/artifact/io.strimzi/mirror-maker-2-extensions/1.1.0) or [1.0.0](https://mvnrepository.com/artifact/io.strimzi/mirror-maker-2-extensions/1.0.0).**