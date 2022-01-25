# Wordplay - Word Search Puzzle Solver

> Developed by Rifqi Naufal Abdjul - 13520062

## Description

Wordplay merupakan program untuk mencari kata dalam word search puzzle dengan menggunakan pendekatan bruteforce

## Requirements

- Java SE 11

## Usage

### Build

Untuk melakukan build terhadap program ikuti langkah berikut ini

1. Mulai command prompt/powershell, ubah directory ke src
2. Jalankan kode berikut ini `javac -d ../bin *.java` untuk meng-compile .class ke dalam build
3. Ubah directory ke bin, lalu jalankan kode berikut `jar cvf wordplay.jar *`
4. file .jar akan terbentuk dan dapat dijalankan

### Run

#### using .jar

1. Mulai command prompt/powershell, ubah directory ke bin
2. gunakan kode berikut ini `java -cp wordplay.jar wordplay ../test/{nama file .txt}`

#### using .class

1. Mulai command prompt/powershell, ubah directory ke bin
2. gunakan kode berikut ini `java wordplay ../tests/{nama file .txt}`
