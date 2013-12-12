package latihan.sidratul.ajax.spring.controller;

import java.util.List;
import latihan.sidratul.ajax.spring.dao.ProdukDao;
import latihan.sidratul.ajax.spring.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/produk")
public class ProdukController {
    @Autowired private ProdukDao produkDao;
    
    @RequestMapping("/tampil")
    public void tampilProduk(ModelMap modelMap){
        List<Produk> produks= produkDao.getAllProduk();
        modelMap.addAttribute("listProduk", produks);
    }
    
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public void formInputProduk(@RequestParam(required = false,value = "id") Integer id, ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        if(produk==null){
            produk = new Produk();
        }
        
        modelMap.addAttribute("Produk",produk);
    }
    
    @RequestMapping(value = "/input",method = RequestMethod.POST)
    public String prosesInputProduk(@ModelAttribute Produk produk, ModelMap modelMap){
        produkDao.saveProduk(produk);
        return "redirect:tampil";
    }
    
    @RequestMapping("/hapus")
    public String hapusProduk(@RequestParam("id") Integer id, ModelMap modelMap){
        produkDao.deleteProduk(id);
        return "redirect:tampil";
    }
}