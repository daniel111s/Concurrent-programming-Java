# Concurrent programming project in Java


## Problem: Supermarket checkout service simulation
### Description:
The task is to optimize the customer service process in a store with multiple cash registers and a certain number of maximum customers in the store (N). The following are the main assumptions of this task:

Number of Checkouts (M): The store is equipped with M cash registers. Each cash register is operated by one cashier.

Maximum Number of Customers (N): The store can have a maximum of N customers at one time.

Separate Queues: There is a separate queue of customers waiting to be served for each cash register. Customers in the queue wait for free checkouts.

Cashiers' Breaks: Cashiers have to go on breaks during certain periods of time. During breaks, a particular cash register cannot be serviced. Each cash register has a specific break time.

Changing Cashiers: When the cashier who was on a break finishes, another cashier takes over the operation of his cash register.

Closing the Cash Register: Each cash register must be closed during the change of cashiers.

Customer Service: Customers are served in the order in which they joined the queue. 

Time Range: The task covers a specific time range within which customer service must be optimized.

Waiting for Service: Customers in a queue wait their turn until they are served by an available cashier. 

The goal of this task is to create an algorithm or system that optimizes the customer service process in such a way that the waiting time is as short as possible and service is efficient despite cashiers' interruptions and the need to close cash registers at certain points in time. It is also worth taking into account

* M - number of cash registers
* N - maximum number of customers in the store
* Separate queues for each cash register
* Cashiers' breaks and their change after a certain period of time (the cash register is at that 
 time closed at that time)
* Completion of service to all customers from the queue before the checkout is closed.


### Simulation:
After starting the program, a window appears where the user can enter data, namely the number of customers in the store, the number of cash registers in the store, and the number of customers after which the cash register can have a break. The parameters are loaded by entering the corresponding values in the fields "Number of cash registers", "Number of customers" and "Number of customers before break". After entering the numbers, press the "START" button to start the simulation operation. After starting the program, a real-time simulation of serving N customers at M cash registers is performed, which have a break after serving a given number of customers. When all customers have been served, then the program execution ends. The circle symbolizes the customer while the rectangle symbolizes the cash register, which turns yellow if the cash register has a break.

<img width="1075" alt="Zrzut ekranu 2023-10-21 o 15 35 48" src="https://github.com/daniel111s/Concurrent-programming-Java/assets/126720512/4124f50f-bb34-4008-be89-acc2f8fcbf61">

![Zrzut ekranu 2023-10-21 o 15 37 20](https://github.com/daniel111s/Concurrent-programming-Java/assets/126720512/8fee4a0d-6d43-4da4-8897-573d4c518798)

![Zrzut ekranu 2023-10-21 o 15 37 40](https://github.com/daniel111s/Concurrent-programming-Java/assets/126720512/ebce379d-5cc8-4638-b36f-53041faf53d1)




## Technologies
Project is created with:
* Java
* JavaFX
	
