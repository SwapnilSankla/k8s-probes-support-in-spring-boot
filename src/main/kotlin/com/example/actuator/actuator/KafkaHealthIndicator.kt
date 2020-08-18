package com.example.actuator.actuator

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeClusterOptions
import org.apache.kafka.clients.admin.KafkaAdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component("kafkaProbe")
class KafkaHealthIndicator : HealthIndicator {
	override fun health(): Health {
		val adminClient = KafkaAdminClient.create(mapOf(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092"))
		val nodeCount = adminClient.getNodeCount()
		if (nodeCount == 0) return Health.down().withDetail("nodeCount", nodeCount.toString()).build()
		return Health.up().withDetail("nodeCount", nodeCount.toString()).build()
	}

	private fun AdminClient.getNodeCount(): Int {
		return try {
			this
					.describeCluster(DescribeClusterOptions().timeoutMs(1000))
					.nodes()
					.get()
					.size
		} catch (e: Exception) {
			0
		}
	}
}