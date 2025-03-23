<h3 align="center">
<pre>
  _____    _____             _____  _____  _____  _   _  ______  _____  
 |  __ \  / ____|   /\      / ____||_   _|/ ____|| \ | ||  ____||  __ \ 
 | |__) || (___    /  \    | (___    | | | |  __ |  \| || |__   | |__) |
 |  _  /  \___ \  / /\ \    \___ \   | | | | |_ || . ` ||  __|  |  _  / 
 | | \ \  ____) |/ ____ \   ____) | _| |_| |__| || |\  || |____ | | \ \ 
 |_|  \_\|_____//_/    \_\ |_____/ |_____|\_____||_| \_||______||_|  \_\
                                                                        
                                                                        
</pre>
</h3>

<h4 align="center">
  A simple Java program for blind signing and veryfing using RSA algorithm.
</h4>


<div align="center">
  
  ![JavaFx](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)
  ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
  
</div>


<p align="center">
  <a href="#overview">Overview</a> •
  <a href="#key-features">Key Features</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#license">License</a> 
</p>
<h4 align="center">
<!--   <img src="preview.gif" alt=""> -->
</h4>

## Overview
In general digital signatures use asymmetric cryptography with a private and a public key.Hash of the message is encrypted with the private key to create the signature. The public key is then used to decrypt (verify) the signature. If the decrypted hash matches the original message's hash, it confirms the message's authenticity and that it was sent by the owner of the private key. This program also simulates blind signatures, which work like regular digital signatures but with an extra step: the message is "blinded" (in this case additionally encrypted) before being signed. This ensures that the signer cannot see the message's content, making it useful when the sender and signer are different entities.


This project was created for Cryptography course on Technical University of Lodz.


## Key Features
* Generating RSA keys of specified length
* Regular and blind signatures
* Veryfing signatures

## How To Use
In order to run this application Java and Maven are required.

## License

Apache-2.0 
