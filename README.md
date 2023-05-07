# PhoneStore
This is  Application is based on singleton class architecture, whereas multple tasks are running concurrently.
On first page I am displaying currently available products(in my case phones) aand orders.
As soon as new Order gets placed dashboard will be updated as the task associated with it will get call.
If order placed the product will be no longer available, and its quantity will be decreased.
Also if the product returned it will be displayed in products table as available.

This project has a Datasource as Singleton object throughout the process.
It will avoid the object ambiguty as it will not create any other virtual object in memory.
This will help in less memory usage as if project grows memory can be an issue.

I have added testing classes using jUnit library in test folder.

I have visualize the database tables for the fields I am using in this application and put in image.
It will help to understand for its object relation mapping.

Also I have tried a UML to explain its behavior of this multithreaded application. 
Both diagrams will be available in UML folder.


