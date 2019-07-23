# HR and Payroll System

URL : 
Pelamar

Pegawai

Proyek
daftar : /proyek
detail : /proyek-detail/1
ubah : /pegawai-ubah/1
tambah : /proyek-tambah/
hapus : /pegawai-hapus?id=1

Kehadiran

Produk

Akun


# HR and Payroll System

proyek sistem informasi untuk client yang meliputi fitur HR dan Payroll

## Getting Started

### Code Conference
Snake_Case : Aji_Wuryanto

Kalimat tanya sebagai penanda boolean : isEmpty

Kata kerja sebagai fungsi : submitPelamar

Kalimat tanya sebagai penanda boolean : isEmpty

Kata kerja sebagai fungsi : submitPelamar

Kata benda sebagai variable/object/attribut : pelamar

### Bahasa Pemrograman

Java 8.0

### Framework
Springboot sebagai backend

Thymeleaf sebagai frontend

## Cara Mulai Mengerjakan

1. Pull dari master
2. Drop semua table dengan db yg ada di application.properties
3. run program
4. import account.sql



## TODO

no owner

anti cenayang security

maxlength untuk input yg string sesuai dengan model//nanti

pegawai outsourcing controller line 160 maksudnya apa? biar no produk gk keambil, bakal berlaku buat proyek juga

abis penggajian kemana? ke list absensi proyek

kalau pegawai dihapus, apakah semua riwayat dan feedback nya juga kehapus? kehapus

apakah riwayat kerja bisa dihapus dan di ubah? gk bisa

tombol2 di kehadiran kok gitu? bisa jadi miss pas merge

kelola gaji di detail proyek maksudnya apa? garubeji

nasib dari proyek dan produk di feedback/riwayat abis kedua itu dihapus? sudah ke handle

apakah kehadiran yg udah di gaji mau ditandain?

front end akun//tinggal hias/design

front end login//tinggal hias/design

cek notifikasi apa aja yg kurang

modal modal

cek view

check image

print format//pelamar sama pegawai (DONE)

nael

dimas
boostrap tidak pakai 3.3.7 alternatif lainnya :) cari cara si navbar nya jadi selaras dengan yg lain
	UbahPegawai.html
	pelamar-daftar.html
	DetailPegawai.html harusnya pake yg 3.30, cek pas mock UAT
	pelamar-detail.html //harusnya udah bener
	pelamar-ubah.html //harusnya udah bener

aji
[x] jika proyek unik, gk ada penggajian 
reorder id kalau ada yg kehapus dan jadi berantakan
playaround dengan csrf nya
samain end point


## Version Control

gitlab

## Java Documentation
1. Diatas kelas dan method, ketik : "/**" lalu tekan enter
2. Isi sesuai format tersedia
3. Contoh isian:
/**
    * Fitur mengubah pelamar : POST request
	* 
	* @param id      id_pelamar
	* @param pelamar Pelamar yang sudah diubah
	* @param model   Model
	* @return Halaman HTML detail pelamar
*/


## Authors

Aji Wuryanto

Athifah Fidelia 

Savira Santoso

Brian Estadimas

Nathanael Lemmuella

