-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2019 at 09:35 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hr-payroll`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `name`, `password`, `role`, `username`) VALUES
(1, 'admin', '$2a$10$ldYxhf9lQJgatap6ieoU1uJXEWE1aR00NfVB82sjUL69q0pF7vu8i', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL,
  `feedback` varchar(255) NOT NULL,
  `proyek` varchar(50) NOT NULL,
  `tanggal_pengisian_feedback` date DEFAULT NULL,
  `id_pegawai` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gaji`
--

CREATE TABLE `gaji` (
  `id` bigint(20) NOT NULL,
  `insentif` bigint(20) DEFAULT NULL,
  `penambahan_lain_lain` bigint(20) DEFAULT NULL,
  `pengurangan_lain_lain` bigint(20) DEFAULT NULL,
  `pinjaman` bigint(20) DEFAULT NULL,
  `potongan` bigint(20) DEFAULT NULL,
  `total_tunjangan` bigint(20) DEFAULT NULL,
  `id_pegawai` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kehadiran`
--

CREATE TABLE `kehadiran` (
  `id` bigint(20) NOT NULL,
  `judul_kehadiran` varchar(255) NOT NULL,
  `jumlah_absen` int(11) NOT NULL,
  `jumlah_cuti` int(11) NOT NULL,
  `jumlah_hari_kerja` int(11) NOT NULL,
  `jumlah_izin` int(11) NOT NULL,
  `jumlah_kehadiran` int(11) NOT NULL,
  `jumlah_lain_lain` int(11) NOT NULL,
  `jumlah_off` int(11) NOT NULL,
  `jumlah_sakit` int(11) NOT NULL,
  `id_pegawai` bigint(20) NOT NULL,
  `id_proyek` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pegawai_outsourcing`
--

CREATE TABLE `pegawai_outsourcing` (
  `id` bigint(20) NOT NULL,
  `bpjsk` varchar(15) DEFAULT NULL,
  `bpjstk` varchar(15) DEFAULT NULL,
  `end_date` date NOT NULL,
  `gaji_pokok` int(11) DEFAULT NULL,
  `jabatan` varchar(20) NOT NULL,
  `jaminan` varchar(25) DEFAULT NULL,
  `join_date` date NOT NULL,
  `nama_bank` varchar(20) DEFAULT NULL,
  `nip` varchar(20) NOT NULL,
  `no_arsip` varchar(5) DEFAULT NULL,
  `no_rekening` varchar(15) DEFAULT NULL,
  `npwp` varchar(15) DEFAULT NULL,
  `pkwt` varchar(15) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `tunjangan_tetap` int(11) DEFAULT NULL,
  `tunjangan_tidak_tetap` int(11) DEFAULT NULL,
  `id_pelamar` bigint(20) NOT NULL,
  `produk` bigint(20) DEFAULT NULL,
  `proyek` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pelamar`
--

CREATE TABLE `pelamar` (
  `id` bigint(20) NOT NULL,
  `agama` varchar(15) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `apply_date` date NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(12) NOT NULL,
  `is_pegawai` bit(1) DEFAULT NULL,
  `jurusan` varchar(50) NOT NULL,
  `lulusan_tahun` varchar(5) DEFAULT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `nama_panggilan` varchar(50) DEFAULT NULL,
  `nama_sekolah` varchar(50) NOT NULL,
  `no_ktp` varchar(16) NOT NULL,
  `nomor_handphone` varchar(16) NOT NULL,
  `nomor_whatsapp` varchar(16) DEFAULT NULL,
  `pendidikan_terakhir` varchar(20) NOT NULL,
  `produk_dilamar` varchar(50) NOT NULL,
  `region` varchar(50) NOT NULL,
  `status_marital` varchar(20) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `telepon_orang_terdekat` varchar(16) DEFAULT NULL,
  `telepon_rumah` varchar(16) DEFAULT NULL,
  `tempat_sekolah` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengalaman_pelamar`
--

CREATE TABLE `pengalaman_pelamar` (
  `id` bigint(20) NOT NULL,
  `alasan_berhenti` varchar(255) NOT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  `lama_bulan_bekerja` varchar(5) DEFAULT NULL,
  `lama_tahun_bekerja` varchar(5) DEFAULT NULL,
  `nama_perusahaan` varchar(100) NOT NULL,
  `tahun_terakhir` varchar(5) DEFAULT NULL,
  `id_pengalaman_pelamar` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` bigint(20) NOT NULL,
  `nama_produk` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `proyek`
--

CREATE TABLE `proyek` (
  `id` bigint(20) NOT NULL,
  `npwp_klien` varchar(15) DEFAULT NULL,
  `deskripsi_proyek` varchar(255) DEFAULT NULL,
  `end_date_kontrak` date NOT NULL,
  `jenis_proyek` varchar(10) NOT NULL,
  `nama_cp` varchar(20) DEFAULT NULL,
  `nama_proyek` varchar(30) NOT NULL,
  `nilai_kontrak` bigint(20) DEFAULT NULL,
  `no_rekening` varchar(50) DEFAULT NULL,
  `no_telp_cp` varchar(16) DEFAULT NULL,
  `nomor_kontrak` varchar(50) NOT NULL,
  `region` varchar(50) NOT NULL,
  `start_date_kontrak` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `riwayat_kerja_pegawai`
--

CREATE TABLE `riwayat_kerja_pegawai` (
  `id` bigint(20) NOT NULL,
  `end_date` date NOT NULL,
  `join_date` date NOT NULL,
  `produk` varchar(50) NOT NULL,
  `proyek` varchar(50) NOT NULL,
  `id_pegawai` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `variable_gaji`
--

CREATE TABLE `variable_gaji` (
  `id` bigint(20) NOT NULL,
  `bpjsk` float DEFAULT NULL,
  `bpjstk` float DEFAULT NULL,
  `ptkp` int(11) DEFAULT NULL,
  `persenan_pph` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKegepplbks8ai64358uh0pgjv6` (`id_pegawai`);

--
-- Indexes for table `gaji`
--
ALTER TABLE `gaji`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmfkdy4pegduephd7v8p37sffv` (`id_pegawai`);

--
-- Indexes for table `kehadiran`
--
ALTER TABLE `kehadiran`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnviwg7u4irfx1oxdgd5uxgulp` (`id_pegawai`),
  ADD KEY `FKqkn15k7lesiw7w7cw9afpcbgf` (`id_proyek`);

--
-- Indexes for table `pegawai_outsourcing`
--
ALTER TABLE `pegawai_outsourcing`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8fj396j2sq269ptxw0kvh0213` (`nip`),
  ADD UNIQUE KEY `UK_8mf359f52j865wh544ssrwugx` (`id_pelamar`),
  ADD KEY `FKhlkn0m4j9o8inwk5plqd4o1wb` (`produk`),
  ADD KEY `FKne13211w7kjn1o7pksaun8xox` (`proyek`);

--
-- Indexes for table `pelamar`
--
ALTER TABLE `pelamar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pengalaman_pelamar`
--
ALTER TABLE `pengalaman_pelamar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs0mmswcsbi7rg0ptvwyqikuqs` (`id_pengalaman_pelamar`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `proyek`
--
ALTER TABLE `proyek`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `riwayat_kerja_pegawai`
--
ALTER TABLE `riwayat_kerja_pegawai`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoor1sob2wav6w9da34rph5wxq` (`id_pegawai`);

--
-- Indexes for table `variable_gaji`
--
ALTER TABLE `variable_gaji`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gaji`
--
ALTER TABLE `gaji`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kehadiran`
--
ALTER TABLE `kehadiran`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pegawai_outsourcing`
--
ALTER TABLE `pegawai_outsourcing`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pelamar`
--
ALTER TABLE `pelamar`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengalaman_pelamar`
--
ALTER TABLE `pengalaman_pelamar`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `proyek`
--
ALTER TABLE `proyek`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `riwayat_kerja_pegawai`
--
ALTER TABLE `riwayat_kerja_pegawai`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `variable_gaji`
--
ALTER TABLE `variable_gaji`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FKegepplbks8ai64358uh0pgjv6` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai_outsourcing` (`id`);

--
-- Constraints for table `gaji`
--
ALTER TABLE `gaji`
  ADD CONSTRAINT `FKmfkdy4pegduephd7v8p37sffv` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai_outsourcing` (`id`);

--
-- Constraints for table `kehadiran`
--
ALTER TABLE `kehadiran`
  ADD CONSTRAINT `FKnviwg7u4irfx1oxdgd5uxgulp` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai_outsourcing` (`id`),
  ADD CONSTRAINT `FKqkn15k7lesiw7w7cw9afpcbgf` FOREIGN KEY (`id_proyek`) REFERENCES `proyek` (`id`);

--
-- Constraints for table `pegawai_outsourcing`
--
ALTER TABLE `pegawai_outsourcing`
  ADD CONSTRAINT `FKhlkn0m4j9o8inwk5plqd4o1wb` FOREIGN KEY (`produk`) REFERENCES `produk` (`id`),
  ADD CONSTRAINT `FKjl9fqwogu4sdti0wa4mk6s557` FOREIGN KEY (`id_pelamar`) REFERENCES `pelamar` (`id`),
  ADD CONSTRAINT `FKne13211w7kjn1o7pksaun8xox` FOREIGN KEY (`proyek`) REFERENCES `proyek` (`id`);

--
-- Constraints for table `pengalaman_pelamar`
--
ALTER TABLE `pengalaman_pelamar`
  ADD CONSTRAINT `FKs0mmswcsbi7rg0ptvwyqikuqs` FOREIGN KEY (`id_pengalaman_pelamar`) REFERENCES `pelamar` (`id`);

--
-- Constraints for table `riwayat_kerja_pegawai`
--
ALTER TABLE `riwayat_kerja_pegawai`
  ADD CONSTRAINT `FKoor1sob2wav6w9da34rph5wxq` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai_outsourcing` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
