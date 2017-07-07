# Integration and Messaging Projects

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