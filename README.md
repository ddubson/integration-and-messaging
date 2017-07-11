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

#### Payload Type Router

Routes based on the type of payload enclosed in Message

![](https://github.com/ddubson/integration-and-messaging/blob/master/assets/router-payload-type.png)


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