BedLogic Tests
==============

CURL commands to test bedlogic resource endpoints. Commands assume bedlogic server IP is 192.168.56.10. Replace with your dns/IP if it is different.

For keymark account type, replace <usercode> by your user code.

Hello
---------

```sh
# Guest User
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/hello"
```


Item Search
---------------

# Guest User
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?itemcode=AECBUB217NR,CRDBARBRU440"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?colorhues=White&size=12X9"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?seriesname=builder"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?inactivecd=N&showonwebsite=Y"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?size=12X9,10X8,4X8"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?lengthmax=2&lengthmin=1"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?seriescolor=Beige&countryorigin=China&category=BRECCIA"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?seriesname=Parquet&colorhues=Beige&lengthmax=20&countryorigin=China&matcategory=Trim&materialstyle=SF&materialclass=CTMNF&inactivecd=N"

# Keymark User
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/ims?itemcode=AECBUB217NR,CRDBARBRU440"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/ims?colorhues=White&size=12X9"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/ims?seriesname=builder"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/ims?inactivecd=N&showonwebsite=Y"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/ims?seriesname=Parquet&colorhues=Beige&lengthmax=20&countryorigin=China&matcategory=Trim&materialstyle=SF&materialclass=CTMNF&inactivecd=N"

# Bad usertype/usercode:
curl --basic -u "guest:x" --get "http://192.168.56.10:8080/bedlogic/v2/ims?itemcode=AECBUB217NR"
curl --basic -u "keymark:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?itemcode=AECBUB217NR"
curl --basic -u "badusertype:" --get "http://192.168.56.10:8080/bedlogic/v2/ims?itemcode=AECBUB217NR"

Item Creation
-----------------
curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:<usercode> -X POST http://192.168.56.10:8080/bedlogic/v2/ims -d '{"itemcode":"TEST","itemcategory":"ATHENA","itemdesc":{"itemdesc1":"2x2 Athena Mosaic on 12x12 SHT Ash"}}' 

Item Update
-----------------
curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:<usercode> -X PUT http://192.168.56.10:8080/bedlogic/v2/ims -d '{"itemcode":"TEST","itemcategory":"AT","itemdesc":{"itemdesc1":"update"}}' 

Item Deletion
-----------------
curl -H "Accept: application/json" -H "Content-type: application/json" -i --user keymark:<usercode> -X DELETE http://192.168.56.10:8080/bedlogic/v2/ims -d '{"itemcode":"TEST"}' 
