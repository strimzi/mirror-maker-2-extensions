/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.kafka.connect.mirror;

/**
 * The IdentityReplicationPolicy is used to replicate topics using Mirror Maker 2 without changing its name. This class
 * originally implemented such policy. But since Kafka 3.0.0, such policy is part of Kafka itself and Strimzi does not
 * need any more its own. It is still supported in Strimzi deployments for backwards compatibility reasons, but it now
 * only inherits from the original Kafka policy.
 */
public class IdentityReplicationPolicy extends org.apache.kafka.connect.mirror.IdentityReplicationPolicy {
    // This class just extends the Apache Kafka for backwards compatibility.
}