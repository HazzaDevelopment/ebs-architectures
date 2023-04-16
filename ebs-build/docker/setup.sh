awslocal s3api create-bucket --bucket renderer-bucket

awslocal iam create-role --role-name lambda-ex --assume-role-policy-document "{"Version": "2012-10-17","Statement": [{ "Effect": "Allow", "Principal": {"Service": "lambda.amazonaws.com"}, "Action": "sts:AssumeRole"}]}"

awslocal iam create-policy --policy-name my-policy --policy-document file://policy.txt

awslocal iam attach-role-policy --policy-arn arn:aws:iam::000000000000:policy/my-policy --role-name lambda-ex

awslocal lambda create-function --function-name renderer-ebs --environment=Variables="{ENVIRONMENT=docker,JDBC_PASSWORD=postgres}" --zip-file fileb://../../ebs-serverless-ingestor/target/ebs-serverless-ingestor-1.0-SNAPSHOT.jar --handler org.ebs.ingestor.Handler::handleRequest --runtime java8 --memory-size 1024 --role arn:aws:iam::000000000000:role/lambda-ex
awslocal lambda create-function --function-name ingest-ebs --environment=Variables="{ENVIRONMENT=docker,JDBC_PASSWORD=postgres}" --zip-file fileb://../../ebs-serverless-renderer/target/ebs-serverless-renderer-1.0-SNAPSHOT.jar --handler org.ebs.renderer.Handler::handleRequest --runtime java8 --memory-size 1024 --role arn:aws:iam::000000000000:role/lambda-ex

awslocal sqs create-queue --queue-name renderer-queue
awslocal sqs create-queue --queue-name ingest-queue

awslocal sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/renderer-queue --attribute-names All

awslocal lambda create-event-source-mapping --function-name renderer-ebs --batch-size 5 --maximum-batching-window-in-seconds 60  --event-source-arn arn:aws:sqs:us-east-1:000000000000:renderer-queue
awslocal lambda create-event-source-mapping --function-name ingest-ebs --batch-size 5 --maximum-batching-window-in-seconds 60  --event-source-arn arn:aws:sqs:us-east-1:000000000000:ingest-queue