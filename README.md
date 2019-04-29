# HR and Payroll System

URL : 
daftar : /proyek
detail : /proyek-detail/1
ubah : /pegawai-ubah/1
tambah : /proyek-tambah/
hapus : /pegawai-hapus?id=1



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

1. Clone ke pc/laptop (git clone <url>) jangan lupa add remote, git add remote <nama_remote> <url>
2. Import project lewat STS
3. Tunggu STS configurasi otomatis
4. Bikin branch baru dengan nama usecase kalian (git branch <nama_use_case>)
5. Pindah ke branch tersebut (git checkout <nama_branch_yang_tadi_dibuat>)
6. Mulai Mengerjakan
7. Jika sudah, push ke branch kalian (git status -> git add . -> git commit -m "commit messages", -> git push <nama_remote> <branch_kalian>)

## TODO

Dimas : 

[] Constraint Start dan End date proyek

[] Ubah proyek autofill region

[] Size kolom

[] Search hanya untuk kolom tertentu

[] seragamkan front end

[] seluruh tombol sudah diimplementasikan bisa dipencet

[] Notifikasi ketika proyek sudah di buat,ubah,hapus

[] Notifikasi ketika regulasi pegawai sudah diganti

[] Modal ketika hapus proyek

Nael :

[] Konsistensi

[] Ketika ada error saat pengisian, notifikasi error nya di bagian mana

[] Max length

[] Hapus Pelamar debug

[] Penamaan button

[] seluruh tombol sudah diimplementasikan bisa dipencet

[] Notifikasi ketika pelamar/pegawai sudah di buat,ubah,hapus

[] Modal ketika hapus pelamar/pegawai

[] Modal berhenti assign pegawai

Aji :

[x] Constraint number

[x] Status ketika assign

[x] debug usecase 4&7

[] Style Front end //Tunggu dimas

[x] Constraint jumlah hari kerja 

[] Filter&Search dari kehadiran //Tungg dimas

[x] seluruh tombol sudah diimplementasikan bisa dipencet

[x] implement login //Iterasi 2

[x] implement logout //Iterasi 2 

[x] implement manage account	//sisa My Account

[x] implement manage product //DONE

[x] Notifikasi ketika berhasil bikin kehadiran, hapus kehadiran, ubah kehadiran, berhasil assign 

[] printable format untuk kehadiran perjudul

[x] Bug ketika pada proyek kedua dan lebih, saat akan menambahkan kehadiran daftar pegawai tidak kerender

Savira/Athifah : 

[] Busdev
	
	[] Menambah proyek
	
	[] Menghapus proyek
	
	[] Mengubah proyek
	
[] HR

	[] Mendaftar pelamar
	
	[] Ubah pelamar
	
	[] assign pelamar
	
	[] ubah data pegawai
	
	[] assign pegawai

	[] membuat kehadiran

	[] mengubah kehadiran

	[] menghapus kehadiran

	[] penggajian

[] Viewer

	[] melihat daftar pelamar

	[] melihat daftar pegawai

	[] melihat daftar proyek

	[] melihat daftar kehadiran

	[] melihat detail pelamar

	[] melihat detail pegawai

	[] melihat detail proyek

	[] melihat detail kehadiran

//bisa cek file home.html dan homeController.java untuk referensi pengerjaan	

TASK BELUM YG ADA AMBIL :

[] Printable format buat detail pelamar yg ingin diajukan

[] If else buat pelamar, jika sudah jadi pegawai maka nama pelamar tersebut di hidden dari list of pelamar

[] counter2 di home


##FLow iterasi 2

UC 9
list of kehadiran -> do payment -> rekap(ubah kehadiran)[Modal notifikasi] -> form penggajian




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

