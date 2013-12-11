package latihan.sidratul.ajax.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import latihan.sidratul.ajax.spring.dao.KategoriProdukDao;
import latihan.sidratul.ajax.spring.model.KategoriProduk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;


@Repository("kategoriProdukDao")
public class KategoriProdukDaoImpl implements KategoriProdukDao{
    
    private static final String SQL_GETALL_KATEGORIPRODUK="SELECT * FROM `kategori_produk`";
    private static final String SQL_GET_KATEGORIPRODUK_BYID="SELECT * FROM `kategori_produk` where id_kategori=?";
    private static final String SQL_DELETE_KATEGORIPRODUK_BYID="DELETE FROM `kategori_produk` where id_kategori=?";
    private static final String SQL_UPDATE_KATEGORIPRODUK_BYID="UPDATE `kategori_produk` SET `nama_kategori` = ? WHERE `id_kategori`=?";
    private static final String SQL_INSERT_KATEGORIPRODUK="INSERT INTO `kategori_produk`(`nama_kategori`)VALUES(?)";
    
    public static final class KategoriProdukParameterizedRowMapper implements 
            ParameterizedRowMapper<KategoriProduk>{

        public KategoriProduk mapRow(ResultSet rs, int i) throws SQLException {
            KategoriProduk kategoriProduk = new KategoriProduk();
            
            kategoriProduk.setIdKat(rs.getInt("id_kategori"));
            kategoriProduk.setNamaKategori(rs.getString("nama_kategori"));
            return kategoriProduk;
        }
    
    }
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired 
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<KategoriProduk> getAllKategori() {
        List<KategoriProduk> kategoriProduks = jdbcTemplate.query(SQL_GETALL_KATEGORIPRODUK, new KategoriProdukParameterizedRowMapper());
        return kategoriProduks;
    }

    public void saveKategori(KategoriProduk kategoriProduk) {
        if(kategoriProduk.getIdKat() != null){
            jdbcTemplate.update(SQL_UPDATE_KATEGORIPRODUK_BYID, new Object[]{
                kategoriProduk.getNamaKategori(),kategoriProduk.getIdKat()
            });
        }else{
            jdbcTemplate.update(SQL_UPDATE_KATEGORIPRODUK_BYID, new Object[]{
                kategoriProduk.getNamaKategori()
            });
        }
    }

    public KategoriProduk getKategoriProdukById(Integer idKategori) {
        //if()
    }

    public void deleteKategori(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
