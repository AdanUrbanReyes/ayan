import awswrangler as wr
import json

bucketName='ayan-sales'

def lambda_handler(event, context):
    ap=event['queryStringParameters']['absolutePath']
    rtr=abs(int(event['queryStringParameters']['rowsToRead'])) + 1
    print(ap, rtr)
    df=wr.s3.read_parquet(path=['s3://{0}/{1}'.format(bucketName,ap)], columns=['first_name', 'last_name', 'email', 'gender'])
    df=df.head(rtr)
    rs= df.to_numpy().tolist()
    rs.insert(0, df.columns.tolist())
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