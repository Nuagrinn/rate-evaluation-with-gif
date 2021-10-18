## How it works?


**Configuration**:

Before running you need to add *application.properties* file to *resources* directory 
and fill in some settings:

_giphy.url=https://api.giphy.com/v1/gifs/random_  - url for setting feign-client to connect to giphy.com  
_giphy.api.key=<*YOUR_API*>_   
_giphy.tag.rich=rich_  - get param to get rich-giphy 
_giphy.tag.broke=broke_ - get param to get broke-giphy

_oer.url=https://openexchangerates.org/api/historical_ - url for setting feign client to connect to openxchangerates.com  
_oer.api_id=<*YOUR_API*>_

_local.url=http://localhost:8080_ - test run local address  

**Main functions:**

*http://localhost:8080/getRandomGIF* - get a random GIF depending on the ruble exchange rate
*http://localhost:8080/getRubRates* - get information about the current and previous ruble exchange rate

**Test configuration**:  

To run tests you need to pass the local url to the GIPHYCLient and OERCLient interface both of them are on line 12 
and then run IntegrationTest files in test directory
