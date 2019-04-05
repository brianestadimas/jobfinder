package com.apap.HrPayrollSystem.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.HrPayrollSystem.Model.RiwayatKerjaPegawaiModel;

public interface RiwayatKerjaPegawai extends JpaRepository<RiwayatKerjaPegawaiModel, Long>{
	List<RiwayatKerjaPegawaiModel> findNipPegawaiOutsourcing(String nip);
}


