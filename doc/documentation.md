# Cara Menggunakan Program
Program ini merupakan program amazing yang dapat menemukan penyelesaian dari berbagai macam matriks. Untuk menjalankan program ini, pertama eksekusi/run dulu programnya.

Setelah program dieksekusi, akan muncul 3 pilihan
```
1. Sistem Persamaan Linear
2. Interpolasi Polinom
3. Keluar
```

Input pilihan program (1/2/3). 
Apabila diinput pilihan 1 atau 2, akan muncul pilihan metode yang dapat digunakan, yaitu :

```
1. Metode eliminasi Gauss
2. Metode eliminasi Gauss-Jordan
```
Input pilihan metode (1/2), dan input juga pilihan tipe inputan :
```
1. File (menyelesaikan matriks yang ada di file external)
2. Keyboard (menyelesaiakn matriks yang diinput ke layar eksekusi program oleh pengguna)
```
## Sistem Persamaan Linear

Pada pilihan "Sistem Persamaan Linear" (pilihan 1) pada menu utama, apabila memilih jenis input File (1), maka pengguna cukup memasukkan nama file (<nama_file>.txt) ke layar, dan program akan memberikan penyelesaian dari matriks augmented yang ada di file tersebut.

Apabila pengguna memilih jenis input Keyboard (2), pengguna harus memasukkan dulu ukuran matriks yang akan diselesaikan (baris kolom),
kemudian memasukkan satu per satu setiap nilai pada matriks augmented yang akan dicari penyelesaiannya. 

```
4 7
1 3 -2  0 2  0  0
2 6 -5 -2 4 -3 -1
0 0  5 10 0 15  5
2 6  0  8 4 18  6
```

Setelah itu, program akan mengeluarkan matriks hasil Gauss/Gauss-Jordan (tergantung pilihan metode) serta hasil persamaannya.

## Interpolasi

Apabila pengguna ingin mencari hasil Interpolasi Polinom (pilihan 2), pengguna perlu memasukkan dulu nilai n, baru kemudian memasukkan
nilai x1 y1, x2 y2, .... dst sampai xn yn.

```
3
1 5
2 10
3 15
```

Setelah itu, program akan menyeluarkan persamaan polinom yang sesuai.
Pengguna dapat mencari nilai f(x) dari persamaan dengan menginput nilai x, dan program akan menampilkan nilai f(x) yang sesuai.

## Output File

Setelah penyelesaian persamaan linear atau interpolasi selesai ditemukan, program akan menawarkan pengguna untuk menyimpan output di file eksternal.

Apabila pengguna ingin menyimpan hasil ke file external, pengguna harus menginput nama file (<nama_file>.txt), dan program akan menuliskan hasil output ke file tersebut.

Program pun selesai digunakan.
