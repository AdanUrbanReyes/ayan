import boto3
import json
import codecs
import csv

s3=boto3.client("s3")
bucketName='ayan-sales'

def lambda_handler(event, context):
    ap=event['queryStringParameters']['absolutePath']
    rtr=abs(int(event['queryStringParameters']['rowsToRead'])) + 1
    print(ap, rtr)
    o=s3.get_object(Bucket=bucketName, Key=ap)
    rs=[]
    for r in csv.reader(codecs.getreader("utf-8")(o["Body"])):
        if len(rs) == rtr:
            break
        rs.append(r)
    return {
        "statusCode":200,
        "headers":{
            "Content-Type":"application/json; charset=utf-8",
            'Access-Control-Allow-Headers':'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token',
            'Access-Control-Allow-Methods':'GET',
            'Access-Control-Allow-Origin':'*'
        },
        "body": json.dumps(rs)
    }