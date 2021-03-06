BedLogic Tests
==============

CURL commands to test bedlogic resource endpoints. Commands assume bedlogic server IP is 192.168.56.23. Replace with your dns/IP if it is different.

For keymark account type, replace <usercode> by your user code.

Locations
---------

```sh
# Guest User
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/locations"
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/locations?locationcodes=125,102"
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/locations?locationregion=Central%20CA%20Region"
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/locations?branchname=Visalia%20Branch"

# Keymark User
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/locations"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/locations?locationcodes=125,102"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/locations?locationregion=Central%20CA%20Region"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/locations?branchname=Visalia%20Branch"

# Bad usertype/usercode:
curl --basic -u "guest:x" --get "http://192.168.56.23:8080/bedlogic/v1/locations"
curl --basic -u "keymark:" --get "http://192.168.56.23:8080/bedlogic/v1/locations"
curl --basic -u "badusertype:" --get "http://192.168.56.23:8080/bedlogic/v1/locations"
```

Products
--------

```sh
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/products?itemcode=AECBUB217NR,CRDBARBRU440"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/products?itemcode=AECBUB217NR,CRDBARBRU440"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/products?seriesname=builder"
# Other query params can be found in BedDataAccess, ProductsDataAccess.php file
```

ProductSeries
-------------

```sh
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/productseries"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/productseries"

# replace <seriesname> with actual series name
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/productseries/<seriesname>"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/productseries/<seriesname>"
```

ProductPromos
-------------

```sh
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/productpromos"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/productpromos"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/productpromos?promocode=CL&promoregion=WC&materialtype=Porcelain"
# Other query params can be found in BedDataAccess, BedDataAccessService, readProductPromos
```

PromoSeries
-----------

```sh
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/promoseries"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/promoseries"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/promoseries?promocode=CL&promoregion=WC&materialtype=Porcelain"
```

Inventory
---------

```sh
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/inventory/GLSECP5858-AL"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/inventory/GLSECP5858-AL"
```

MaterialOrders
--------------

```sh
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/materialorders/GLSECP5858-ET/125"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/materialorders/GLSECP5858-ET/125?opencode=Y"
# Guest - unauthorized
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/materialorders/GLSECP5858-ET/125"
```

Prices
------

```sh
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/prices/GLSECP5858-ET"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/prices/GLSECP5858-ET/26815"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/prices/GLSECP5858-ET/26815/125"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/prices/GLSECP5858-ET/26815/125/125"
# Guest - unauthorized
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/prices/GLSECP5858-ET/26815/125/125"
```

Costs
-----

```sh
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/costs/GLSECP5858-ET"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/costs/GLSECP5858-ET/125"
# Guest - unauthorized
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/costs/GLSECP5858-ET"
```

SlabInventory
-------------

```sh
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/slabinventory/MARAZDOMARSLAB/109"
# Guest - unauthorized
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/slabinventory/MARAZDOMARSLAB/109"
```

SlabCosts
---------

```sh
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/slabcosts/MARAZDOMARSLAB/109/178557/?freightrate=0&deliverycost=0"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/slabcosts/MARAZDOMARSLAB/109/178557/?freightrate=100&deliverycost=0"
# Guest - unauthorized
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/slabcosts/MARAZDOMARSLAB/109/178557/?freightrate=0&deliverycost=0"
# Other query params can be found in BedDataAccessService.php, function readSlabCosts
```

Accounts
--------

```sh
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/accounts?customercode=411703"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/accounts?customername=A&branchcode=2&creditstatus=HOLD"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/accounts/1310"
curl --basic -u "keymark:<usercode>" --get "http://192.168.56.23:8080/bedlogic/v1/accounts/1310/2"
# Guest - unauthorized
curl --basic -u "guest:" --get "http://192.168.56.23:8080/bedlogic/v1/accounts?customercode=411703"
```
