# credit-card-service
The Credit Card  microservice is the primary service to retrieve a Credit Card Details and Create a new Credit Card in the System.

With the help of this service you can retrieve a full list of Credit card details in the system.

A Credit card service holds (among other things) the following properties:
- Id
- Name
- Balance
- CardNO
- Limit

# Methods:

	# Post: 
		Creates all the Credit card details in Database system. Pre condition are checked before storing the details in DB
		
		Conditions:
			1. Credit card should follows Luhn algorithm
			2. Credit card No should be always Numeric
			3. Initial balance is 0
			4. Credit Card Number should upto length of 19
	#Get:
		Retrieves all the credit card details stored in DB system.
		
		
For a full list of properties and endpoints that are available please consult the swagger documentation:   
http://localhost:8082/swagger-ui/index.html  when running locally
# API first design
This api is build using api first design practices

#Running a local workspace
__default__  
Makes sure logging gets sent to console