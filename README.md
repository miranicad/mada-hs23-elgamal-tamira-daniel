# mada-hs23-elgamal-tamira-daniel
Mathematik fur die Datenkommunikation | HS23 | Klasse 3Ib (3iCbb)

- Tamira Leber: [tamira.leber@students.fhnw.ch](mailto:tamira.leber@students.fhnw.ch)
- Daniel Barber: [daniel.barber@students.fhnw.ch](mailto:daniel.barber@students.fhnw.ch)

## Beschreibung
Dieses Projekt besteht aus drei ausführbare Java Klassen und zwei Hilfsklassen für das File Handling und das ElGamal Cipher Format. Die Ausführung der Java Klassen überschreibt allfällige Dateien im `/target` Ordner, wenn diese bereits bestehen.

## KeyGenerator

`KeyGenerator` generiert ein ElGamal-Schlüsselpaar `pk.txt` und `sk.txt` und speichert diese im Ordner [`/target`](target) ab.


## Encryptor

`Encryptor` verschlüsselt das File [`text.txt`](src%2Fmain%2Fresources%2Ftext.txt) (ASCII-Format) mit dem öffentlichen Schlüssel [`pk.txt`](src%2Fmain%2Fresources%2Fpk.txt) im Ordner [`resources`](src%2Fmain%2Fresources) und speichert die verschlüsselte Nachricht im Ordner [`/target`](target) als [`chiffre.txt`](target%2Fchiffre.txt)  ab.



## Decryptor
`Decryptor` entschlüsselt das File [`chiffre.txt`](src%2Fmain%2Fresources%2Fchiffre.txt) mit dem privaten Schlüssel [`sk.txt`](src%2Fmain%2Fresources%2Fsk.txt) im Ordner [`resources`](src%2Fmain%2Fresources) und speichert die entschlüsselte Nachricht im Ordner [`/target`](target) als [`text-d.txt`](target%2Ftext-d.txt) ab.

## Lösung
Die gegebene Datei [`chiffre.txt`](src%2Fmain%2Fresources%2Fchiffre.txt) entschlüsselt mit dem privaten Schlüssel [`sk.txt`](src%2Fmain%2Fresources%2Fsk.txt) ergibt den folgenden Text:

**Gut gemacht!!!!**