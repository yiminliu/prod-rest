bedims-uitest
==============


Hello
---------

```sh
# Guest User
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v2/hello"

```

bedims-uitest
--------------

CURL commands to test bedlogic resource endpoints. Commands assume bedlogic server IP is 192.168.56.23. Replace with your dns/IP if it is different.

For keymark account type, replace <usercode> by your user code.


Landing page URL
-------------------
http://192.168.56.10:8080/ims/index

The above URL will take you to the IMS UI Testing starting page. 

Create/Update/Clone/Delete Item require users login with particular permissions. "admin/password" can be used as the testing login.

Search Item requires no login currently.  
