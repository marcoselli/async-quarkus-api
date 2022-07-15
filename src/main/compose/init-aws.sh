awslocal sqs create-queue --queue-name user-queue
awslocal dynamodb create-table --table-name TB_USER --attribute-definitions AttributeName=userName,AttributeType=S --key-schema AttributeName=id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
awslocal secretsmanager create-secret --name jsonsUnificados --secret-string '{
                                                                                "chave":"valor"
                                                                             }'
