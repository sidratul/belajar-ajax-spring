package latihan.sidratul.ajax.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import latihan.sidratul.ajax.spring.dao.KategoriProdukDao;
import latihan.sidratul.ajax.spring.dao.ProdukDao;
import latihan.sidratul.ajax.spring.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("produkDao")
public class ProdukDaoImpl implements ProdukDao{
    
    private static final String SQL_GETALL_PRODUK="SELECT * FROM `produk`";
    private static final String SQL_GET_PRODUKBYID="SELECT * FROM `produk` where id_produk=?";
    private static final String SQL_DELETE_PRODUKBYID="DELETE FROM `produk` where id_produk=?";
    private static final String SQL_UPDATE_PRODUKBYID="UPDATE `produk` SET "
            + "`produk_id` = ?, `nama_produk` = ?,`harga` = ?,`tgl_update` = ?, `id_kategori` = ?, `gambar` = ? "
            + "WHERE `id_produk` = ?";
    private static final String SQL_INSERT_PRODUK="INSERT INTO `produk`"
            + "(`produk_id`,`nama_produk`,`harga`,`tgl_update`,`id_kategori`,`gambar`)"
            + "VALUES(?,?,?,?,?,?);";
    
    @Autowired private KategoriProdukDao kategoriProdukDao;
    private JdbcTemplate jdbcTemplate;
    
    public final class ProdukParameterizedRowMapper implements ParameterizedRowMapper<Produk>{

        public Produk mapRow(ResultSet rs, int i) throws SQLException {
            Produk produk = new Produk();
            produk.setIdProduk(rs.getInt("id_produk"));
            produk.setProdukId(rs.getString("produk_id"));
            produk.setNamaProduk(rs.getString("nama_produk"));
            produk.setHarga(rs.getBigDecimal("harga"));
            produk.setTglUpdate(rs.getTimestamp("tgl_update"));
            produk.setKategoriProduk(kategoriProdukDao.getKategoriProdukById(rs.getInt("id_kategori")));
            produk.setGambar(rs.getString("gambar"));
            return produk;
        }
    
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }
    
    public List<Produk> getAllProduk() {
        List<Produk> produks = jdbcTemplate.query(SQL_GETALL_PRODUK, new ProdukParameterizedRowMapper());
        return produks;
    }

    public void saveProduk(Produk p) {
        if(p.getIdProduk()!=null){
            jdbcTemplate.update(SQL_UPDATE_PRODUKBYID, new Object[]{
                p.getProdukId(),
                p.getNamaProduk(),
                p.getHarga(),
                p.getTglUpdate(),
                p.getKategoriProduk(),
                p.getGambar(),
                p.getIdProduk()
                    
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_PRODUK, new Object[]{
                p.getProdukId(),
                p.getNamaProduk(),
                p.getHarga(),
                p.getTglUpdate(),
                p.getKategoriProduk(),
                p.getGambar(),
            });
        }
    }

    public Produk getProdukById(Integer id) {
        if(id==null){
            return null;
        }else{
            Produk p= jdbcTemplate.queryForObject(SQL_GET_PRODUKBYID, new ProdukParameterizedRowMapper(),id);
            return p;
        }
    }

    public void deleteProduk(Integer id) {
        jdbcTemplate.update(SQL_DELETE_PRODUKBYID,id);
    }
    
}
