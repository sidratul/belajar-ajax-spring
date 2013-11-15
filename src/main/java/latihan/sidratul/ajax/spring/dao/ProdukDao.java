package latihan.sidratul.ajax.spring.dao;

import java.util.List;
import latihan.sidratul.ajax.spring.model.Produk;

public interface ProdukDao {
    public List<Produk> getAllProduk();
    
    public void saveProduk(Produk p);
    
    public Produk getProdukById(Integer id);
    
    public void deleteProduk(Integer id);
}
