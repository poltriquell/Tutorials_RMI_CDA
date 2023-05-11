# Tutorials_RMI_CDA

# Introduction
This project contains the tutorials for RMI Projects that were implemented in Computació Distribuïda i Aplicacions subject in [Computer Science Engineering](https://grauinformatica.udl.cat/en) at [Universitat de Lleida (UdL)](https://www.udl.cat/ca/en/).

# Tutorial 2.1a : First RMI application
Probar de invocar a los objetos distribuidos, desde vuestro propio ordenador y desde otro ordenador diferente. Responder a las siguientes preguntas: 

1. ¿Se ejecutan correctamente los clientes locales y remotos? Justificar la respuesta. 

```
Los clientes locales se ejecutan a la perfección, en cambio, los clientes remotos no. 

Para probar los clientes remotos, utilizamos los equipos de la clase dentro de una misma red local pero no terminaba de hacer una buena conexión entre estos dispositivos.
```

2. Si alguno de los clientes no se ejecuta correctamente, indicad el error que genera y cuál puede ser la causa del fallo. 

```
Recibíamos el mensaje de error "Connection Refused". 
Parecía que los paquetes RMI llegaban correctamente al destino, pero no se recibían de vuelta o bien eran rechazados por el cliente.
```

3. Buscar en Internet una solución a los posibles problemas que se hayan producido.

```
Según IBM, una solución es aumentar el número máximo de procesos permitidos en el servidor con el comando: ulimit -u unlimited.
```



# Tutorial 2.1b : RMI Callbacks
Probar la aplicación RMI añadiendo varios listener desde diferentes máquinas y finalizándolos.

1. ¿Indicar cuál es el comportamiento de la aplicación cuando se añade un nuevo listener local / remoto?

```
En el servidor, se muestra un mensaje que indica la detección de un nuevo listener registrado y se imprime su información, incluyendo su punto de conexión.

No se ha podido probar los listeners remotos por no tener permisos en los ordenadores de clase.
```

2. ¿Qué pasa cuando se elimina un listener local / remoto? ¿El comportamiento de la aplicación es el adecuado en ambos casos? Justificar la respuesta. 

```
Si el servicio del listener se cierra directamente, lo cual puede suceder si se mata o finaliza abruptamente, el servidor, en la siguiente llamada, detectará que el listener ya no está alcanzable y procederá a eliminarlo automáticamente de su registro. 
Esta detección se realiza para mantener actualizada la lista de listeners activos y garantizar un funcionamiento adecuado del servidor.

No se ha podido probar los listeners remotos por no tener permisos en los ordenadores de clase.
```

4. En el caso de producirse algún problema, ¿cómo se podría solucionar?

```
En local no se ha producido ningún problema. 
```



# Teachers
The teacher who has guided this RMI project is:
* Fernando Cores Prado

# Author

* [Pol Triquell Lombardo](https://github.com/poltriquell)

# License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details

