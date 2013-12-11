package latihan.sidratul.ajax.spring.dao;

import java.util.List;
import latihan.sidratul.ajax.spring.model.KategoriProduk;

public interface KategoriProdukDao {
    public List<KategoriProduk> getAllKategori();
    
    public void saveKategori(KategoriProduk kategoriProduk);
    
    public KategoriProduk getKategoriProdukById(Integer idKategori);
    
    public void deleteKategori(Integer id);
}
