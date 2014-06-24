BedLogic Tests
==============

CURL commands to test bedlogic resource endpoints. Commands assume bedlogic server IP is 192.168.56.10. Replace with your dns/IP if it is different.

For keymark account type, replace <usercode> by your user code.

Hello
---------

```sh
# Guest User
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v2/hello"
```


Products
--------

# Guest User
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/product?itemcode=AECBUB217NR,CRDBARBRU440"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/product?colorhues=White&size=12X9"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/product?seriesname=builder"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/product?inactivecd=N&showonwebsite=Y"
curl --basic -u "guest:" --get "http://192.168.56.10:8080/bedlogic/v2/product?seriesname=Parquet&colorhues=Beige&lengthmax=20&countryorigin=China&matcategory=Trim&materialstyle=SF&materialclass=CTMNF&inactivecd=N"

# Keymark User
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/product?itemcode=AECBUB217NR,CRDBARBRU440"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/product?colorhues=White&size=12X9"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/product?seriesname=builder"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/product?inactivecd=N&showonwebsite=Y"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.10:8080/bedlogic/v2/product?seriesname=Parquet&colorhues=Beige&lengthmax=20&countryorigin=China&matcategory=Trim&materialstyle=SF&materialclass=CTMNF&inactivecd=N"

# Bad usertype/usercode:
curl --basic -u "guest:x" --get "http://192.168.56.10:8080/bedlogic/v2/product?itemcode=AECBUB217NR"
curl --basic -u "keymark:" --get "http://192.168.56.10:8080/bedlogic/v2/product?itemcode=AECBUB217NR"
curl --basic -u "badusertype:" --get "http://192.168.56.10:8080/bedlogic/v2/product?itemcode=AECBUB217NR"
