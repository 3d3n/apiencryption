# apiencryption

This is a POC to send encrypted data to a REST controller, decrypt the data before the REST endpoint processes the data and finally encrypt and return the response after processing. This is done with the controller advice interface provided by spring boot.

**Drawback**:
The content-type of the REST call to the controller is still 'applicaton/json' while sending a text. The solution would be to find a way to be able to change the content-type before data processing.

