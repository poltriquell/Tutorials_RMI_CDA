#!/bin/bash

# Compilar interfase remoto (BombillaRMI.java)
echo "javac BombillaRMI.java"
javac BombillaRMI.java

# Compilar implementación interfase remoto (BombillaRMIServant.java)
echo "javac BombillaRMIServant.java" 
javac BombillaRMIServant.java

# Compilar Servidor (BombillaRMIServer.java)
echo "javac BombillaRMIServer.java"
javac BombillaRMIServer.java

# Compilar cliente (BombillaRMIClient.java)
echo "javac BombillaRMIClient.java"
javac BombillaRMIClient.java