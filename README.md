# TelMarket
TelOne school project 2017-2018 University of Zimbabwe faculty of Engineering for Terrence T Munyunguma. TelOne payment system for e-commerce platform.
This project has been published only for educational purposes. Technologies used are:
Java
Java EE 7
jPos 2.1.1
MySQL MariaDB


## License
Affero GNU Visit [http://jpos.org/license](http://jpos.org/license).
/*
 * Terrence Takunda Munyunguma [https://github.com/TerrenceTakunda]
 * Copyright (C) 2018 ttmunyunguma@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


##DISCLAIMER
=============================

```
 ____      ____  _       _______     ____  _____  _____  ____  _____   ______    _  
|_  _|    |_  _|/ \     |_   __ \   |_   \|_   _||_   _||_   \|_   _|.' ___  |  | | 
  \ \  /\  / / / _ \      | |__) |    |   \ | |    | |    |   \ | | / .'   \_|  | | 
   \ \/  \/ / / ___ \     |  __ /     | |\ \| |    | |    | |\ \| | | |   ____  | | 
    \  /\  /_/ /   \ \_  _| |  \ \_  _| |_\   |_  _| |_  _| |_\   |_\ `.___]  | |_| 
     \/  \/|____| |____||____| |___||_____|\____||_____||_____|\____|`._____.'  (_) 

```

Please note that this project was published solely for educational purposes. For commercial use
please contact the author, purchase a commercial jPOS licence and be responsible.

THANK YOU


## Deployment guide

You will need at least Java 8, glassfish server 5.0 , primefaces 6.2 jar, jpos2.1.1 jar, mysql 8, netbeans 8.2 , git

Git clone the projec repository to a local repo.

##Checkout Module
/*
* Open Netbeans IDE
* Inside Netbeans, click open existing project.
* Select TelMarketCheckout javaweb project
* Add the following libraries to the project(jpos.jar, primefaces.jar, allthemes.jar , jsf.jar)providen in the 00_lib folder
* Deploy the project on glassfish sever
*/

##Gateway Sever
/*
* In your terminal, change directory to 'TelMarketServer/'
* run the command 'git run'
*/
OR
/*
* In your terminal, change directory to 'TelMarketServer/'
* run the command 'git iA'
* Change directory to '/TelMarketServer/build/install/TelMarketServer/'
* run the command 'bin/q2'
*/

##Endpoint Simmulation
/*
* Inside netbeans IDE click open existing project
* Open java project 'MASTERCARD_PN'
* Open java project 'VISA_PN'
* Run both projects
*/

ALL DONE
The Payment gateway PoC should now be running on your local sever


