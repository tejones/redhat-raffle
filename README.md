Red Hat Raffle Web App
====================

This app will serve multiple purposes:

1. Provide embedded A-MQ instance exposed via REST to enable communication from the scanner app to and from the underlying MySQL DB. A set of Python scripts will parse the scanned value and insert into the DB. Then another message will be sent to A-MQ for the scanner app to get a list of scanned attendees.
2. A page will allow random drawings to take place and display the winner.
