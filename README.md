# Integration and Messaging Projects

## Spring Integration

### Basic Integration (basic-integration)

Input Channel -> Service Activator (Endpoint) -> Output Channel

![basic-integration-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/basic-integration-schema.png)

### Pollable Channels

#### Queue Channel

![pollable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/pollable-queue-channel-schema.png)

#### Priority Queue Channel

![pollable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/pollable-priority-queue-channel-schema.png)

### Subscribable Channels

#### Direct Channel

![subscribable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/subscribable-direct-channel-schema.png)

#### Publish-Subscribe Channel

![subscribable-queue-channel-schema](https://github.com/ddubson/integration-and-messaging/blob/master/assets/subscribable-pubsub-channel-schema.png)

### Messaging Bridge

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/message-bridge-schema.png)

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