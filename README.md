# Integration and Messaging Projects

## Spring Integration

### Basic Integration (basic-integration)

```
./gradlew :spring-integration:basic-integration:bootRun
```

![basic-integration-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/basic-integration-schema.png)

### Pollable Channels

#### Queue Channel

```
./gradlew :spring-integration:pollable-channels:queue-channel:bootRun
```

![pollable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/pollable-queue-channel-schema.png)

#### Priority Queue Channel

```
./gradlew :spring-integration:pollable-channels:priority-queue-channel:bootRun
```

![pollable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/pollable-priority-queue-channel-schema.png)

### Subscribable Channels

#### Direct Channel

```
./gradlew :spring-integration:subscribable-channels:direct-channel:bootRun
```

![subscribable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/subscribable-direct-channel-schema.png)

#### Publish-Subscribe Channel

```
./gradlew :spring-integration:subscribable-channels:pubsub-channel:bootRun
```

![subscribable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/subscribable-pubsub-channel-schema.png)

#### Messaging Bridge

```
./gradlew :spring-integration:subscribable-channels:messaging-bridge:bootRun
```

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/message-bridge-schema.png)

#### Channel Interceptors

```
./gradlew :spring-integration:subscribable-channels:channel-interceptors:bootRun
```

### Router

#### Type and Value Routers

Routes based on the type of payload or header value enclosed in Message (Payload Type, Header Value)

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/router-payload-type.png)

#### Recipient List Router

Routes based on the recipient filter expression, routes to default channel if no match.

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/recipient-list-router.png)

### Filter

#### Basic Filter

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/filter-basic-filter.png)

#### Custom Filter

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/filter-custom-filter.png)

### Splitters and Aggregators

#### Basic Splitter

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/basic-splitter.png)

#### Basic Splitter and Aggregator

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/basic-aggregator.png)

#### Custom Aggregator

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/custom-aggregator.png)

### Transformers

#### Basic Transformer

Create a simple transformation inline (SpEL expression based)

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/basic-transformer.png)

#### Custom Transformer

Custom transform function based on an external service

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/custom-transformer.png)

#### Filtering Headers

Filter headers for each message on a message channel 

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/filtering-headers.png)

#### Header Enricher

Add headers for each message on a message channel

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/header-enricher.png)

#### Payload Enricher

Add more data per each message on a channel

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/payload-enricher.png)

### Processing Endpoints

#### Gateways

Gateways can take in raw POJOs and process them.

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/gateways-1.png)

#### Service Activators

Service Activators are endpoints at which data is handled or proxied.

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/service-activators-1.png)

#### Inbound/Outbound Channel Adapters

Inbound adapters receive information from external systems, outbound adapters push data to external systems.

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/inbound-outbound-adapters-1.png)

### File System Integrations

#### Reading Files

Reading from file system

```
./gradlew :spring-integration:file-system-integration:reading-files:bootRun
```

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/reading-files-1.png)

#### Writing Files

Writing files to the file system

```
./gradlew :spring-integration:file-system-integration:writing-files:bootRun
```

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/writing-files-1.png)

#### Transforming Files

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/transforming-files-1.png)

#### Outbound Gateway

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/outbound-gateway-1.png)

### FTP Integrations

#### Inbound Channel Adapters

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/ftp-1.png)

#### Outbound Channel Adapters

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/ftp-2.png)

#### Outbound Gateways

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/ftp-3.png)

---

## File Transfer Pattern Project (file-transfer-pattern)

### Flow

1. Run FTPUploader and create a sample file in `target/upload`
2. Uploader will upload to FTP server
3. Run FTPDownloader, which will download the sample file to `target/download`

Note: Make sure the home directory on FTP server, the user has create/delete permissions on directories.

## Spring AMQP - RabbitMQ Project (spring-amqp-rabbitmq)

### Running

Start RabbitMQ by running `rabbitmq-server` command on macOS

## Spring JMS (spring-jms-simple)

Must have `activemq` installed.

> MacOS: `brew install activemq`

Start activemq via `activemq start`