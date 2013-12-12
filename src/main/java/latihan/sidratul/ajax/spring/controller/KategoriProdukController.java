package latihan.sidratul.ajax.spring.controller;

import java.util.List;
import latihan.sidratul.ajax.spring.dao.KategoriProdukDao;
import latihan.sidratul.ajax.spring.model.KategoriProduk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("kategori-produk")
public class KategoriProdukController {
    @Autowired KategoriProdukDao kategoriProdukDao;
    
    @RequestMapping("/tampil")
    public void tampilKategoriProduk(ModelMap modelMap){
        List<KategoriProduk> kategoriProduks= kategoriProdukDao.getAllKategori();
        modelMap.addAttribute("listKategiriProduk", kategoriProduks);
    }
    
    @RequestMapping(value = "/input",method = RequestMethod.GET)
    public void formInputKategoriProduk(@RequestParam(value = "id",required = false ) Integer id
            ,ModelMap modelMap){
        KategoriProduk kategoriProduk = kategoriProdukDao.getKategoriProdukById(id);
        if(kategoriProduk==null){
            kategoriProduk= new KategoriProduk();
        }
        
        modelMap.addAttribute("kategoriProduk", kategoriProduk);
    }
    
    @RequestMapping(value = "/input",method = RequestMethod.POST)
    public String formInputKategoriProduk(@ModelAttribute KategoriProduk kategoriProduk
            ,ModelMap modelMap){
        
        kategoriProdukDao.saveKategori(kategoriProduk);
        return "redirect:tampil";
    }
    
    @RequestMapping("/hapus")
    public String hapusKategoriProduk(@RequestParam("id") Integer id, ModelMap modelMap){
        kategoriProdukDao.deleteKategori(id);
        return "redirect:tampil";
    }
    
}
