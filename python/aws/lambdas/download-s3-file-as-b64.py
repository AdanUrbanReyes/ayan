import boto3
import json
import base64

s3=boto3.client('s3')
bucketName='ayan-pictures'

def lambda_handler(event, context):
    ap = event['queryStringParameters']['absolutePath']
    print(ap)
    o = s3.get_object(Bucket=bucketName, Key=ap)
    return {
        'statusCode':200,
        'headers':{
            'Content-Type' : '{}'.format(o['ContentType']),
            'Content-Disposition': 'attachment; filename={}'.format(ap.split('/').pop()),
            'Access-Control-Allow-Headers':'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token',
            'Access-Control-Allow-Methods':'GET',
            'Access-Control-Allow-Origin':'*'
        },
        'body': base64.b64encode(o['Body'].read()),
        'isBase64Encoded': True
    }