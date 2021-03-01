# apiencryption

This is a POC to send encrypted data to a REST controller, decrypt the data before the REST enndpoint processes the data and finally encrypt and return response after processing. This is done with the controller advice interface provided by springboot.

**Drawback**
The content-type of the REST call to the controller is still 'applicaton/json' while sending a text. The solution would be to finnnd a way to be able to change the content-type before data processing.

