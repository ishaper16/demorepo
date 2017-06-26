# demorepo
## email records

The project sends csv file containing requested salesforce records (product, account or both) to the requested email address.

### Getting Started

Download zip file to machine.
Then import it to Anypoint Studio as "Anypoint Studio" -> "Anypoint Studio Project from External Location"

### Prerequisites

Need to have installed Anypoint Studio (3.8) in machine

### Deploying

Open src\main\resources\configuration-DEV.properties and give valid values for the properties there.
Then run email-records.

### Testing

After the app is deployed, do following GET requests

-> http://localhost:8081/emailrecords/account?emailTo=test@email.com :
  This will extract account records from salesforce and convert to CSV file and attach it to email. Email body will contain 
  "No. of account records : n"
  
-> http://localhost:8081/emailrecords/product?emailTo=test@email.com :
  This will extract product records from salesforce and convert to CSV file and attach it to email. Email body will contain 
  "No. of product records : n"
  
-> http://localhost:8081/emailrecords/{anything}?emailTo=test@email.com
  This will extract both account and product records from salesforce and convert to separate CSV files and attach it to email. Email body will contain 
  "No. of product records : n
   No. of  account records: m"
