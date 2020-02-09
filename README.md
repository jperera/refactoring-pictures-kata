# Refactoring pictures kata

El objetivo de esta kata es practicar el uso de refactoring mediante *baby steps*, haciendo especial hincapié en mantener los tests en verde durante todo el tiempo.


##Punto de partida

Tenemos una "librería" de gestión de imágenes. La manera de utilizar la funcionalidad de la librería es instanciar la clase PicturesManager, que ofrece acceso al API de la librería:

* Long createPicture(PictureDto picture)
* PictureDto getPicture(Long id)
* void selectAsMainPicture(Long id)
* boolean isMainPicture(Long id)
* void removePicture(Long id)

Las imágenes tienen un orden que se gestiona de manera interna (no se expone en el API pública).
La imagen principal (main picture) es primera en el orden (orden 1).

## Petición de cambio de comportamiento

Nos llega una petición de cambio de comportamiento. Ahora ya no querremos que la imagen principal sea la primera en orden, 
simplemente será la que el usuario marque, sin afectar en nada al orden de las imágenes. Siempre habrá una imagen principal.
Si el usuario no ha indicado todavía cuál es esta, habrá una por defecto.

Debemos implementar este comportamiento sin romper en ningún momento el comportamiento visible, es decir, los tests deben mantenerse en verde en todo momento. 
