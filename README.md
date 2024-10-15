# **SimpleCPDSim**

A simple CPD system simulator in Java
Project Description

## Description

The CPD simulator is based on a model of requests and data processing. The system consists of several modules, each with its own role:

- **Source (Fuente):** Generates requests periodically and sends them to the CPD.
- **Data Processing Center (CPD):** Receives the requests, processes them, and stores them until they can be sent to the output modules.
- **Sink (Sumidero):** Receives and handles the responses from the CPD, simulating the reception of processed responses.

The simulation uses threads (multithreading) to model concurrency and asynchronous processing of requests.
## Features

- CPD parameter selection: 
    * Number of cores
    * Queue size
- Simulation parameter selection:
    * Time between requests
    * Request system time
    * Maximum request number
- Simulation output files
- Real-Time Request processing

## Requirements

- Java 8 or higher

- Java IDE (such as IntelliJ, Eclipse) or command line to compile and run the code.
## Input Variables

To run this project, you will need to add the following arguments in the command line:

`MIN_T_INTER` Minimum interval time between requests

`MAX_T_INTER` Maximum interval time between requests

`MIN_T_SERV` Minimum service time

`MAX_T_SERV` Maximum service time

`MIN_LLEGADAS` Total number of requests



## Installation

1. Clone the repository

```bash
git clone https://github.com/xpabolleta/SimpleCPDSim.git
cd SimpleCPDSim
```
2. Compile the code (if using command line)

```bash
javac -d target src/main/java/*.java
```
3. Run the program
```bash
cd target
java Main [Args]
```