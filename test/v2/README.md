BedLogic Tests
==============

CURL commands to test bedlogic resource endpoints. Commands assume bedlogic server IP is 192.168.56.23. Replace with your dns/IP if it is different.

For keymark account type, replace <usercode> by your user code.

Hello
---------

```sh
# Guest User
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v2/hello"
```