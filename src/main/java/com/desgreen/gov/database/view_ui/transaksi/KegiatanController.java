package com.desgreen.gov.database.view_ui.transaksi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.desgreen.gov.database.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.application.AppSetting;
import com.desgreen.gov.database.jpa_repository.TbKegiatanRepository;
import com.desgreen.gov.database.jpa_repository.TbOpdMenuRepository;
import com.desgreen.gov.database.jpa_repository.TbDesaRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisJalanRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisPrasaranaRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisSaranaRepository;
import com.desgreen.gov.database.jpa_repository.TbKabupatenRepository;
import com.desgreen.gov.database.jpa_repository.TbKecamatanRepository;
import com.desgreen.gov.database.jpa_repository.TbKegiatanJenisRepository;
import com.desgreen.gov.database.jpa_repository.TbKegiatanLokasiPicsRepository;
import com.desgreen.gov.database.jpa_repository.TbKegiatanLokasiRepository;
import com.desgreen.gov.database.jpa_repository.TbOpdRepository;
import com.desgreen.gov.database.jpa_repository.TbStatusJalanRepository;
import com.desgreen.gov.database.jpa_repository.TbSumberDanaRepository;
import com.desgreen.gov.database.jpa_repository.TbKegiatanRepository;
import com.desgreen.gov.database.jpa_repository.TbKegiatanRepository;
import com.desgreen.gov.database.jpa_repository.UserRolesRepository;
import com.desgreen.gov.database.jpa_repository.UsersRepository;
import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbJenisJalan;
import com.desgreen.gov.database.model.TbJenisPrasarana;
import com.desgreen.gov.database.model.TbJenisSarana;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbKegiatan;
import com.desgreen.gov.database.model.TbKegiatanJenis;
import com.desgreen.gov.database.model.TbKegiatanLokasi;
import com.desgreen.gov.database.model.TbKegiatanLokasiPics;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbOpdMenu;
import com.desgreen.gov.database.model.TbPrasarana;
import com.desgreen.gov.database.model.TbSarana;
import com.desgreen.gov.database.model.TbStatusJalan;
import com.desgreen.gov.database.model.TbSumberDana;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbKegiatan;
import com.desgreen.gov.database.model_enum.EnumSatuanUnit;
import com.desgreen.gov.database.model_enum.Role;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class KegiatanController {

    private static final Logger logger = LoggerFactory.getLogger(KegiatanController.class);
    
    String currentUserName = "";
    String currenPrincipal = "";

    @Autowired
    private TbOpdRepository opdRepository;
    @Autowired
    private TbOpdMenuRepository opdMenuRepository;
    
    @Autowired
    private TbSumberDanaRepository sumberDanaRepository;
    @Autowired
    private TbKegiatanJenisRepository kegiatanJenisRepository;
    @Autowired
    private TbJenisPrasaranaRepository jenisPrasaranaRepository;
    @Autowired
    private TbJenisSaranaRepository jenisSaranaRepository;

    @Autowired
    private TbKecamatanRepository kecamatanRepository;
    @Autowired
    private TbDesaRepository desaRepository;

    @Autowired
    private TbKegiatanRepository kegiatanRepository;
    @Autowired
    private TbKegiatanLokasiRepository kegiatanLokasiRepository;
    @Autowired
    private TbKegiatanLokasiPicsRepository kegiatanLokasiPicsRepository;


    @Autowired
    private TbJenisJalanRepository jenisJalanRepository;
    @Autowired
    private TbStatusJalanRepository statusJalanRepository;


    @Autowired
    private SecurityUtils securityUtils;

    private TbOpd currentOpd = new TbOpd();    
    private TbJenisSarana currentJenisSapras = new TbJenisSarana();

    // private Integer currentJenisSapras = 0;    

    private TbKegiatan currentKegiatan = new TbKegiatan();
    private TbKegiatanLokasi currentKegiatanLokasi = new TbKegiatanLokasi();


    private List<TbKegiatanLokasi> listKegiatanLokasi = new ArrayList<>();

    private List<TbKegiatanJenis> listKegiatanjenis = new ArrayList<>();
    private List<TbSumberDana> listSumberDana = new ArrayList<>();
    private List<TbOpd> listOpd = new ArrayList<>();
    private List<TbJenisPrasarana> listJenisPrasarana = new ArrayList<>();
    private List<TbJenisSarana> listJenisSarana = new ArrayList<>();

    private List<TbKecamatan> listKecamatan = new ArrayList<>();
    private List<TbDesa> listDesa = new ArrayList<>();

    private Map<Integer, String> mapJenisSapras = new HashMap<>();

    // private static String UPLOADED_FOLDER = "/Users/yhawin/gambarnya/";

    @ModelAttribute("allEnumSatuanUnit")
    public EnumSatuanUnit[] getAllEnumSatuanUnit() {
        return EnumSatuanUnit.values();
    }

    // @ModelAttribute("currentOpd")
    // public TbOpd getCurrentOpd() {
    //     return currentOpd;
    // }    
    // @ModelAttribute("currentJenisSapras")
    // public TbJenisSarana getCurrentJenisSapras() {
    //     return currentJenisSapras;
    // }

    @ModelAttribute("listStatusJalan")
    public List<TbStatusJalan> getListStatusJalan() {
        return statusJalanRepository.findAll();
    }
    @ModelAttribute("listFungsiJalan")
    public List<TbJenisJalan> getListFungsiJalan() {
        return jenisJalanRepository.findAll();
    }
    
    

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/kegiatan")
    public String listIndex(@RequestParam("opdId") final String opdId, final Model viewModel) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String namaUser = auth.getName(); //jika tidak ada akan memberikan nilai null
            currentUserName = namaUser;
            currenPrincipal = auth.getPrincipal().toString();            
        } catch (Exception e) {            
        }


        try {
            if (! opdId.isEmpty()) {
                TbOpd tbOpd = new TbOpd();
                tbOpd = opdRepository.findById(Integer.parseInt(opdId));       
                currentOpd = tbOpd;
            }
        } catch (Exception e) {
        }

        listJenisPrasarana = jenisPrasaranaRepository.findAll();
        listJenisSarana = jenisSaranaRepository.findAll();

        for (TbJenisPrasarana domain: listJenisPrasarana) {
            mapJenisSapras.put(domain.getId(), domain.getDescription());
        }
             
        viewModel.addAttribute("currentOpd", currentOpd);
        currentJenisSapras = new TbJenisSarana();
        viewModel.addAttribute("currentJenisSapras", currentJenisSapras);
 

        List<TbKegiatan> listAllData = new ArrayList<>();
        // if (currenPrincipal.contains("ROLE_ADMIN") ) {
        //     viewModel.addAttribute("allData", kegiatanRepository.findAll() );
        // }else {
        // }
        // listAllData = kegiatanRepository.findAllByOpd(currentOpd.getId());

        viewModel.addAttribute("allData", listAllData );
    // 
        // viewModel.addAttribute("mapJenisSapras", mapJenisSaprasMenu(currentOpd.getId()));
        viewModel.addAttribute("mapJenisSapras", mapJenisSaprasMenu(currentOpd) );


        if (currentOpd==null) {
            return "redirect:/home";
        }
        logger.info("# Form Task");


        return "kegiatan/kegiatan_list"; 
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/kegiatan_jenisSapras")
    public String listIndexJenisSapras(@RequestParam("opdId") final String opdId, @RequestParam("jenisSaprasId") final String jenisSaprasId, final Model viewModel) {
       
       if (jenisSaprasId.equals("0")) {
        return "redirect:/kegiatan?opdId=" + opdId;
       } 

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String namaUser = auth.getName(); //jika tidak ada akan memberikan nilai null
            currentUserName = namaUser;
            currenPrincipal = auth.getPrincipal().toString();            
        } catch (Exception e) {            
        }


        try {
            if (! opdId.isEmpty()) {
                TbOpd tbOpd = new TbOpd();
                tbOpd = opdRepository.findById(Integer.parseInt(opdId));       
                currentOpd = tbOpd;
            }
        } catch (Exception e) {
        }
        if (! jenisSaprasId.isEmpty()) {
            TbJenisSarana jenisSapras = new TbJenisSarana();
            jenisSapras = jenisSaranaRepository.findById(Integer.parseInt(jenisSaprasId));
            currentJenisSapras = jenisSapras;
        }                

        listJenisPrasarana = jenisPrasaranaRepository.findAll();
        listJenisSarana = jenisSaranaRepository.findAll();

        for (TbJenisPrasarana domain: listJenisPrasarana) {
            mapJenisSapras.put(domain.getId(), domain.getDescription());
        }
             
        viewModel.addAttribute("currentOpd", currentOpd);
        viewModel.addAttribute("currentJenisSapras", currentJenisSapras);


        List<TbKegiatan> listAllData = new ArrayList<>();
        listAllData = kegiatanRepository.findAllByOpd(currentOpd.getId(), currentJenisSapras.getId());

        viewModel.addAttribute("allData", listAllData );
    // 
        // viewModel.addAttribute("mapJenisSapras", mapJenisSaprasMenu(currentOpd.getId()));
        viewModel.addAttribute("mapJenisSapras", mapJenisSaprasMenu(currentOpd) );


        if (currentOpd==null) {
            return "redirect:/home";
        }
        logger.info("# Form Task");

        return "kegiatan/kegiatan_list"; 
    }

 

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/kegiatan_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbKegiatan domain, @ModelAttribute("domainDetil1") final TbKegiatan domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbKegiatan newDomain = new TbKegiatan();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription1(domain.getDescription1() ) ;
            newDomain.setDescription2(domain.getDescription2() ) ;

            newDomain.setPanjangVolume(domain.getPanjangVolume());
            newDomain.setEnumSatuanUnit( domain.getEnumSatuanUnit() );

            newDomain.setTahunAnggaran(domain.getTahunAnggaran());         
            double nilaiPagu = Double.valueOf(domain.getNilaiPagu());   
            newDomain.setNilaiPagu(nilaiPagu);

            newDomain.setSumberDanaBean(domain.getSumberDanaBean()) ;
            newDomain.setKegiatanJenisBean(domain.getKegiatanJenisBean() ) ;

            newDomain.setJenisPrasaranaBean(domain.getJenisPrasaranaBean());
            newDomain.setJenisSaranaBean(domain.getJenisSaranaBean());

            newDomain.setPrasaranaBean(domain.getPrasaranaBean());

            // newDomain.setOpdBean(domain.getOpdBean());
            newDomain.setOpdBean(currentOpd);         
            // if (newDomain.getOpdBean().equals(null) ) {
            //     newDomain.setOpdBean(currentOpd);
            // }

        
            if (newDomain.getOpdBean() !=null && newDomain.getJenisSaranaBean() !=null) {
                if (kegiatanRepository.save(newDomain) != null) {
                    redirectAttributes.addFlashAttribute("saveUser", "success");
                    newDomain.setTempInt1(1);
                    return "redirect:/kegiatan/edit_form/" + newDomain.getId();    
                } else {
                    redirectAttributes.addFlashAttribute("saveUser", "fail");
                }
            }

            newDomain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
    

        }else if (domain.getTempInt1()==1) {
            TbKegiatan domainUpdate = kegiatanRepository.findById(domain.getId() );

            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription1(domain.getDescription1());

            domainUpdate.setPanjangVolume(domain.getPanjangVolume());
            domainUpdate.setEnumSatuanUnit( domain.getEnumSatuanUnit() );

            domainUpdate.setTahunAnggaran(domain.getTahunAnggaran());      
            double nilaiPagu = Double.valueOf(domain.getNilaiPagu());   
            domainUpdate.setNilaiPagu(nilaiPagu);

            domainUpdate.setOpdBean(domain.getOpdBean());
            domainUpdate.setSumberDanaBean(domain.getSumberDanaBean()) ;
            domainUpdate.setKegiatanJenisBean(domain.getKegiatanJenisBean() ) ;

            domainUpdate.setJenisPrasaranaBean(domain.getJenisPrasaranaBean());
            domainUpdate.setJenisSaranaBean(domain.getJenisSaranaBean());

            domainUpdate.setPrasaranaBean(domain.getPrasaranaBean());

            if (domainUpdate.getOpdBean().equals(null) ) {
                domainUpdate.setOpdBean(currentOpd);
            }

            if (domainUpdate.getOpdBean() !=null && domainUpdate.getJenisSaranaBean() !=null) {
                kegiatanRepository.save(domainUpdate);
            }
            redirectAttributes.addFlashAttribute("success_info", domain.getEnumSatuanUnit() );

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/kegiatan/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/kegiatan/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/kegiatan/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/kegiatan/operation: {} ", operation);

        model.addAttribute("currentOpd", currentOpd);
        model.addAttribute("currentJenisSapras", currentJenisSapras);
  
       if (operation.equals("delete")) {
            try {
                TbKegiatan domainToDelete = kegiatanRepository.findById(id);        
                // hati-hati opdRepository.deleteAll(domainToDelete.getListDesa() );
                try {
                    kegiatanLokasiRepository.deleteAll(domainToDelete.getListKegiatanLokasi());                    
                } catch (Exception e) {
                }
                try {
                    kegiatanRepository.delete(domainToDelete);                                   
                } catch (Exception e) {
                }


                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");


            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbKegiatan newDomain = new TbKegiatan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                
                newDomain.setTahunAnggaran(LocalDate.now().getYear()); //default tahun ini
                
                newDomain.setOpdBean(currentOpd);
                newDomain.setJenisSaranaBean(currentJenisSapras);

                model.addAttribute("domain", newDomain);    
                
               listOpd = opdRepository.findAll();
               listSumberDana = sumberDanaRepository.findAll();
               listKegiatanjenis = kegiatanJenisRepository.findAll();

               listJenisPrasarana = jenisPrasaranaRepository.findAll();
               listJenisSarana = jenisSaranaRepository.findAll();
        

                model.addAttribute("listOpd", listOpd);
                model.addAttribute("listSumberDana", listSumberDana);
                model.addAttribute("listKegiatanjenis", listKegiatanjenis);

                
                model.addAttribute("listJenisPrasarana", listJenisPrasarana);
                model.addAttribute("listJenisSarana", listJenisSarana);


                // listKegiatanLokasi = kegiatanLokasiRepository.findAll();
                listKegiatanLokasi = newDomain.getListKegiatanLokasi();
                model.addAttribute("listKegiatanLokasi", listKegiatanLokasi);


                return "kegiatan/kegiatan_form";
            }

        } else if (operation.equals("edit_form")) {

            // TbKegiatan domain = new TbKegiatan();
            // TbKegiatan domain = (TbKegiatan) kegiatanRepository.findById(id).get();
            // List<TbKegiatan> listDomain = kegiatanRepository.findAll();
            TbKegiatan domain = kegiatanRepository.findById(id);   

            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                
                listOpd = opdRepository.findAll();
                listSumberDana = sumberDanaRepository.findAll();
                listKegiatanjenis = kegiatanJenisRepository.findAll();


                listJenisPrasarana = jenisPrasaranaRepository.findAll();
                listJenisSarana = jenisSaranaRepository.findAll();

                if (domain.getOpdBean()==null) domain.setOpdBean(currentOpd);
        
                model.addAttribute("domain", domain);      

                model.addAttribute("listOpd", listOpd);
                model.addAttribute("listSumberDana", listSumberDana);
                model.addAttribute("listKegiatanjenis", listKegiatanjenis);
                
                model.addAttribute("listJenisPrasarana", listJenisPrasarana);
                model.addAttribute("listJenisSarana", listJenisSarana);
             
                // listKegiatanLokasi = kegiatanLokasiRepository.findAllByParentId(domain.getId());            
                listKegiatanLokasi = domain.getListKegiatanLokasi();

                model.addAttribute("listKegiatanLokasi", listKegiatanLokasi);

                currentKegiatan = domain;

                return "kegiatan/kegiatan_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/kegiatan_jenisSapras?opdId=" + currentOpd.getId()+ "&jenisSaprasId=" + currentJenisSapras.getId();
        // return "redirect:/kegiatan/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/kegiatan_lokasi_form/save_process"}, method = RequestMethod.POST)
    // public String saveProcess_Lokasi(@ModelAttribute("domain") final TbKegiatanLokasi domain, @ModelAttribute("domainDetil1") final TbKegiatan domainDetil1, 
    //                 @RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2, final RedirectAttributes redirectAttributes) {
    public String saveProcess_Lokasi(@ModelAttribute("domain") final TbKegiatanLokasi domain, @ModelAttribute("domainDetil1") final TbKegiatan domainDetil1, 
    @RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2, final RedirectAttributes redirectAttributes) {
            
        if (domain.getTempInt1()==0) {
            TbKegiatanLokasi newDomain = new TbKegiatanLokasi();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setTagLat1(domain.getTagLat1());
            newDomain.setTagLon1(domain.getTagLon1());
            newDomain.setTagLat2(domain.getTagLat2());
            newDomain.setTagLon2(domain.getTagLon2());
            newDomain.setNilaiDana(domain.getNilaiDana());

            newDomain.setKegiatanBean(domain.getKegiatanBean() ) ;

            newDomain.setKecamatanBean(domain.getKecamatanBean());
            newDomain.setDesaBean(domain.getDesaBean() ) ;
           
        
  
            if (kegiatanLokasiRepository.save(newDomain) != null) {
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1);

          //Upload Image
          if (! file_1.isEmpty()) {
            String newImageName = System.currentTimeMillis() + "__" + file_1.getOriginalFilename();
            try {

                byte[] bytes = file_1.getBytes();
                // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Path path = Paths.get(AppSetting.APP_IMAGE_PATH + newImageName);
                Files.write(path, bytes);

                TbKegiatanLokasiPics newKegiatanLokasiPic = new TbKegiatanLokasiPics();
                newKegiatanLokasiPic.setKegiatanLokasiBean(newDomain);
                newKegiatanLokasiPic.setDescription(newImageName);
                newKegiatanLokasiPic.setImageName(newImageName);
                newKegiatanLokasiPic.setTitle(newImageName);

                kegiatanLokasiPicsRepository.save(newKegiatanLokasiPic);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (! file_2.isEmpty()) {
            String newImageName = System.currentTimeMillis() + "__" + file_2.getOriginalFilename();
            try {

                byte[] bytes = file_2.getBytes();
                // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Path path = Paths.get(AppSetting.APP_IMAGE_PATH + newImageName);
                Files.write(path, bytes);

                TbKegiatanLokasiPics newKegiatanLokasiPic = new TbKegiatanLokasiPics();
                newKegiatanLokasiPic.setKegiatanLokasiBean(newDomain);
                newKegiatanLokasiPic.setDescription(newImageName);
                newKegiatanLokasiPic.setImageName(newImageName);
                newKegiatanLokasiPic.setTitle(newImageName);

                kegiatanLokasiPicsRepository.save(newKegiatanLokasiPic);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

                return "redirect:/kegiatan_lokasi/edit_form/" + newDomain.getId();    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

            newDomain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
    
        }else if (domain.getTempInt1()==1) {
            TbKegiatanLokasi domainUpdate = kegiatanLokasiRepository.findById(domain.getId() );

            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setTagLat1(domain.getTagLat1());
            domainUpdate.setTagLon1(domain.getTagLon1());
            domainUpdate.setTagLat2(domain.getTagLat2());
            domainUpdate.setTagLon2(domain.getTagLon2());
            domainUpdate.setNilaiDana(domain.getNilaiDana());

            domainUpdate.setKegiatanBean(domain.getKegiatanBean() ) ;


            domainUpdate.setKecamatanBean(domain.getKecamatanBean());
            domainUpdate.setDesaBean(domain.getDesaBean() ) ;

            kegiatanLokasiRepository.save(domainUpdate);

            //Upload Image
            if (! file_1.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_1.getOriginalFilename();
                try {

                    byte[] bytes = file_1.getBytes();
                    // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);

                    TbKegiatanLokasiPics newKegiatanLokasiPic = new TbKegiatanLokasiPics();
                    newKegiatanLokasiPic.setKegiatanLokasiBean(domainUpdate);
                    newKegiatanLokasiPic.setDescription(newImageName);
                    newKegiatanLokasiPic.setImageName(newImageName);
                    newKegiatanLokasiPic.setTitle(newImageName);

                    kegiatanLokasiPicsRepository.save(newKegiatanLokasiPic);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (! file_2.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_2.getOriginalFilename();
                try {

                    byte[] bytes = file_2.getBytes();
                    // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                    Path path = Paths.get(AppSetting.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);

                    TbKegiatanLokasiPics newKegiatanLokasiPic = new TbKegiatanLokasiPics();
                    newKegiatanLokasiPic.setKegiatanLokasiBean(domainUpdate);
                    newKegiatanLokasiPic.setDescription(newImageName);
                    newKegiatanLokasiPic.setImageName(newImageName);
                    newKegiatanLokasiPic.setTitle(newImageName);

                    kegiatanLokasiPicsRepository.save(newKegiatanLokasiPic);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/kegiatan_lokasi/edit_form/" + domain.getId();
        }
           
        
        return "redirect:/kegiatan/lokasi/";
    }

    @PreAuthorize("hasAnyRole({'" + Role.USER + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/kegiatan_lokasi/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation_Lokasi(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/kegiatanlokasi/operation: {} ", operation);


       if (operation.equals("delete")) {
            try {
                TbKegiatanLokasi domainItemToDelete = kegiatanLokasiRepository.findById(id);        
                // hati-hati opdRepository.deleteAll(domainToDelete.getListDesa() );

                
                try {
                    kegiatanLokasiRepository.delete(domainItemToDelete);                                   
                } catch (Exception e) {
                }
            
                // redirectAttributes.addFlashAttribute("msg", "del");
                // redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
                
                currentKegiatan.setTempInt1(1);
                // newDomain.setTempInt1(1);
                return "redirect:/kegiatan/edit_form/" + currentKegiatan.getId(); 


            } catch (Exception e) {

                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbKegiatanLokasi newDomain = new TbKegiatanLokasi();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                
                newDomain.setKegiatanBean(currentKegiatan);
                
                model.addAttribute("domain", newDomain);    
                listDesa = desaRepository.findAll();
                listKecamatan = kecamatanRepository.findAll();

                model.addAttribute("listDesa", listDesa);
                model.addAttribute("listKecamatan", listKecamatan);
            
                return "kegiatan/kegiatanlokasi_form";

            }

        } else if (operation.equals("edit_form")) {

            TbKegiatanLokasi domain = kegiatanLokasiRepository.findById(id);   

            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                

                listDesa = desaRepository.findAll();
                listKecamatan = kecamatanRepository.findAll();

                model.addAttribute("domain", domain);      
                
                model.addAttribute("listDesa", listDesa);
                model.addAttribute("listKecamatan", listKecamatan);
             

                model.addAttribute("allData_LokasiPics", domain.getListKegiatanLokasiPics());                

                currentKegiatanLokasi = domain;

                return "kegiatan/kegiatanlokasi_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/kegiatan/lokasi/";
    }
   

    @PreAuthorize("hasAnyRole({'" + Role.USER + "', '" + Role.USER + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/kegiatan_lokasi_pics/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation_LokasiPics(@PathVariable("operation") final String operation,
    @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
    final Model model) {

        if (operation.equals("delete")) {
                    try {
                        TbKegiatanLokasiPics domainItemPics_ToDelete = kegiatanLokasiPicsRepository.findById(id);        
                        // hati-hati opdRepository.deleteAll(domainToDelete.getListDesa() );

                    
                        Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainItemPics_ToDelete.getImageName());
                        Files.deleteIfExists(path);

                        try {
                            kegiatanLokasiPicsRepository.delete(domainItemPics_ToDelete);                                   
                        } catch (Exception e) {
                        }
                        
                        currentKegiatan.setTempInt1(1);
                        // newDomain.setTempInt1(1);
                        return "redirect:/kegiatan_lokasi/edit_form/" + currentKegiatanLokasi.getId(); 


                    } catch (Exception e) {

                        redirectAttributes.addFlashAttribute("msg", "del_fail");
                        redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                        e.printStackTrace();
                    }
                
        }

        return "redirect:/kegiatan/lokasi/";
    }

    public Map<Integer, String> mapJenisSaprasMenu(TbOpd opd) {
        mapJenisSapras.clear();
        List<TbOpdMenu> listOpdMenu = opdMenuRepository.findAllBy_OpdId(opd.getId() );

        for (TbOpdMenu opdMenuBean: listOpdMenu) {
            mapJenisSapras.put(opdMenuBean.getJenisSaranaBean().getId(), opdMenuBean.getJenisSaranaBean().getDescription() );         
        }

        return mapJenisSapras;
    }

    public Map<Integer, String> mapJenisSaprasMenu(int opdId) {
        mapJenisSapras.clear();

        if (opdId==3){ //dinas pekerjaan umum
            mapJenisSapras.put(1, "Jaringan Jalan");
            mapJenisSapras.put(5, "Jembatan");
            mapJenisSapras.put(20, "Drainase");
            mapJenisSapras.put(4, "Sanitasi");
            mapJenisSapras.put(2, "Air Bersih");

        }if (opdId==11) { //dinas Perikanan
            mapJenisSapras.put(5, "Cool Storage (Belum ada di Sapras)");
            mapJenisSapras.put(17, "Pasar Ikan (Adanya perikanan)");

        }if (opdId==1) { //dinas Pendidikan
            mapJenisSapras.put(0, "Sekolah (Belum ada di sapras)");

        }if (opdId==9) { //Dinas perhubungan
            mapJenisSapras.put(0, "Sarana Perhubungan(Belum ada di sapras)");

        }if (opdId==4) { //Dinas Perkimtan
            mapJenisSapras.put(7, "Perkimban (kurang spesific)");

        }if (opdId==2) { //Dinas Kesehatan
            mapJenisSapras.put(0, "Kesehatan (kurang spesific)");

        }if (opdId==12) { //Dinas Pariwisata
            mapJenisSapras.put(0, "Fasilitas Pariwisata (Kurang spesific)");

        }if (opdId==12) { //Dinas Pertanian
            mapJenisSapras.put(0, "Fasilitas Pertanian (Kurang spesific)");

        }if (opdId==7) { //Dinas Ketahanan Pangan
            mapJenisSapras.put(0, "Fasilitas Ketahanan Pangan (Kurang spesific)");

        }if (opdId==9999) { //Dinas BPBD BELUM ADA DI MENU
            mapJenisSapras.put(0, "Fasilitas Penanggulanan Bencana(tidak ada di sapras)");

        }if (opdId==10) { //Dinas Kominfo
            mapJenisSapras.put(0, "Fasilitas Kominfo(kurang spesific)");

        }if (opdId==5) { //Dinas Sosial
            mapJenisSapras.put(0, "Fasilitas Sosial(kurang spesific)");

        }if (opdId==8) { //Dinas Lingkungan Hidup
            mapJenisSapras.put(0, "Fasilitas Lingkungan Hidup (kurang spesific)");

        }if (opdId==6) { //Dinas Naker Trans
            mapJenisSapras.put(0, "Saluran Ai (Tidak ada di Sapras)");
            mapJenisSapras.put(4, "Jaringan Listrik");

        }

        return mapJenisSapras;
    }

}
