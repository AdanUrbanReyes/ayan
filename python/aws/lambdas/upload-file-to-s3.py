import boto3
import json
import base64
import email
import os

s3=boto3.client('s3')
bucketName='ayan/pictures'
rootDirectory='living/'

def lambda_handler(event, context):
    db=base64.b64decode(event['body'])
    t=event['headers']['content-type']
    t='Content-Type: '+t+'\n'
    msg = email.message_from_bytes(t.encode()+db)
    if msg.is_multipart():
        multipart_content = {}
        for pl in msg.get_payload():
            if pl.get_filename():
                fn = pl.get_filename()
            multipart_content[pl.get_param('name', header='content-disposition')] = pl.get_payload(decode=True)
        t = rootDirectory + event['queryStringParameters']['title'] + os.path.sep
        t = t + fn
        s3_upload = s3.put_object(Bucket=bucketName, Key=t, Body=multipart_content['files'])
        return {
            'statusCode':200
            , 'headers':{
                'Access-Control-Allow-Headers':'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token',
                'Access-Control-Allow-Methods':'POST',
                'Access-Control-Allow-Origin':'*'
            }
            , 'body': json.dumps(fn + 'uploaded')
        }
    else:
        return {
            'statusCode':500
            , 'headers':{
                'Access-Control-Allow-Headers':'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token',
                'Access-Control-Allow-Methods':'POST',
                'Access-Control-Allow-Origin':'*'
            }
            , 'body': json.dumps('The received content (' + ct + ') is not multipart')
        }