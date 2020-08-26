/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.kafka.connect.mirror;

import java.util.Map;
import org.apache.kafka.connect.mirror.DefaultReplicationPolicy;
import org.apache.kafka.connect.mirror.MirrorConnectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *   This replication policy is supposed to be used for a simple two-site active-passive Kafka DRP setup where mirroring is always enabled
 *   in one direction only and where names of the downstream topics are not prefixed by Mirror Maker 2, so that switching of the active site
 *   1) does not require reconfiguration of the business services (i.e., we do not have to change names of the topics the services use) and
 *   2) does not add one more prefix to the topic name (i.e., we do not have topic names growing each time the switch happens).
 * </p>
 * <p>
 *   This solution seems to work with Mirror Maker version 2.4.0 but it is 'hacky' because it violates spec of the ReplicationPolicy
 *   interface. According to the spec, if <code>upstreamTopic</code> returns null then <code>topicSource</code> should also return null,
 *   but with this implementation it is not the case. We had to make <code>topicSource</code> return alias of the upstream cluster because
 *   otherwise mirroring of topic properties (e.g., "cleanup.policy=compact") did not work.
 * </p>
 */

public class IdentityReplicationPolicy extends DefaultReplicationPolicy {

    private static final Logger log = LoggerFactory.getLogger(IdentityReplicationPolicy.class);

    private String sourceClusterAlias;

    @Override
    public void configure(Map<String, ?> props) {
        super.configure(props);
        sourceClusterAlias = (String) props.get(MirrorConnectorConfig.SOURCE_CLUSTER_ALIAS);
        if (sourceClusterAlias == null) {
            String logMessage = String.format("Property %s not found", MirrorConnectorConfig.SOURCE_CLUSTER_ALIAS);
            log.error(logMessage);
            throw new RuntimeException(logMessage);
        }
    }

    @Override
    public String formatRemoteTopic(String sourceClusterAlias, String topic) {
        return topic;
    }

    @Override
    public String topicSource(String topic) {
        return topic == null ? null : sourceClusterAlias;
    }

    @Override
    public String upstreamTopic(String topic) {
        return null;
    }

}