# README #
### What is this repository for? ###

* Design Digital Wallet system for Machine coding round or LLD design.

* It will focus on the design of a Digital Wallet system like Google Pay or Paytm. But this design will be on a much smaller scale. We can’t obviously design complex systems like Google Pay or Paytm in 2-3 hours.

* This solution can also be used for the LLD(Low-Level Design) round. You can use the same solution with some tweaks here and there.

### Requirements of Digital Wallet System ###
Let’s first understand the requirements and the problem statement.

### Mandatory Requirements ###
* We are supposed to create a digital wallet system that allows people to transfer amounts between their wallets.
* The wallet uses its own currency known as Rupee(₹).
* The account balance cannot drop below ₹ 0.0.
* The smallest amount that can be transferred between wallets is ₹ 0.01.
* The user should be presented with options for each action. And the options are as follows:
  * Create Wallet – This option should create a wallet for the user.
  * Transfer Amount – This option should enable the transfer of funds from one account to the other.
  * Account Statement – This option should display the account statement for the specified user.
  * Overview – This option should display all the account numbers currently in the system. Additionally, it should show the current balances for these accounts.
  * Exit – The system should exit.

### Optional Requirements ###
These are the requirements that are not mandatory, but good to have. Let’s go through the optional requirements.
* There can be N numbers of offer for which user(sender/receiver) will be eligibil, example:
  * Offer 1 – When the amount is transferred from user A to user B, 10% of the transfer amount will be added to sender user as cashback.
  * Offer 2 – If account balance of both user gets equals then 5% of the transfer amount will be added to sender user as cashback.

### About Code ###
* Implemented using core-Java, so flow will be run at Terminal/console.
  * Design pattern: SingleTon and Factory.
* ApplicationDriver: Is the the driver/runner file or starting point of the service/code.
