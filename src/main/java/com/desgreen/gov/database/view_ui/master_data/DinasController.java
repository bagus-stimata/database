package com.desgreen.gov.database.view_ui.master_data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.desgreen.gov.database.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.jpa_repository.TbOpdRepository;
import com.desgreen.gov.database.jpa_repository.TbSaranaRepository;
import com.desgreen.gov.database.jpa_repository.TbJenisSaranaRepository;
import com.desgreen.gov.database.jpa_repository.TbKabupatenRepository;
import com.desgreen.gov.database.jpa_repository.TbKecamatanRepository;
import com.desgreen.gov.database.jpa_repository.TbOpdMenuRepository;
import com.desgreen.gov.database.jpa_repository.TbOpdRepository;
import com.desgreen.gov.database.jpa_repository.TbOpdRepository;
import com.desgreen.gov.database.jpa_repository.UserRolesRepository;
import com.desgreen.gov.database.jpa_repository.UsersRepository;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbOpdMenu;
import com.desgreen.gov.database.model.TbSarana;
import com.desgreen.gov.database.model.FUserRoles;
import com.desgreen.gov.database.model.TbJenisSarana;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbOpd;
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
public class DinasController {

    private static final Logger logger = LoggerFactory.getLogger(DinasController.class);

    @Autowired
    private TbKecamatanRepository kecamatanRepository;

    @Autowired
    private TbOpdRepository opdRepository;
    @Autowired
    private TbOpdMenuRepository opdMenuRepository;

    @Autowired
    private TbJenisSaranaRepository jenisSaranaRepository;


    @Autowired
    private SecurityUtils securityUtils;


    @ModelAttribute("allOpdMenus")
    public List<String> getAllOpdMenus() {
        List<String> list = new ArrayList<>();
        for (TbJenisSarana jenisSarana: jenisSaranaRepository.findAll()) {
            list.add(jenisSarana.getKode1() + ". " + jenisSarana.getDescription()); //dikasih kode biar aman dari double
        }      
        list.sort(Comparator.naturalOrder());  
        return list;
    }
    
    @ModelAttribute("opdMenuArrayList")
    public List<TbOpdMenu> getAllOpdMenuArrayList() {
        List<TbJenisSarana> listJenisSarana = jenisSaranaRepository.findAll();
        List<TbOpdMenu> opdMenuArrayList = new ArrayList<>();                
        for (TbJenisSarana jenisSaranaBean: listJenisSarana) {
            TbOpdMenu newOpdMenu = new TbOpdMenu();
            // newOpdMenu.setOpdBean(domain);
            newOpdMenu.setJenisSaranaBean(jenisSaranaBean);    
            opdMenuArrayList.add(newOpdMenu);
        }                

        return opdMenuArrayList;
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/dinas")
    public String listIndex(final Model viewModel) {
  
        final TbOpd newDomain =new TbOpd();
        viewModel.addAttribute("newDomain", newDomain);

        List<TbOpd> list = new ArrayList<>();
        for (TbOpd domain: opdRepository.findAll()) {
            list.add(domain);
        }

        viewModel.addAttribute("allData", list);

        logger.info("# Form Task");
      
        return "master/dinas/dinas_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/dinas_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbOpd domain,  final TbOpd domainDetil1, final RedirectAttributes redirectAttributes) {
       
        String message = "";
        int counter = 0;
        // for (String nilai: domain.getTempOpdMenus()){
        //     message += ++counter + ". " +  nilai + " >> ";
        // }        
        // redirectAttributes.addFlashAttribute("message", message);

        if (domain.getTempInt1()==0) {
            TbOpd newDomain = new TbOpd();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());

            //New Tidak perlu delete
            // if (newDomain.getListOpdMenu().size()>0)
            //     opdMenuRepository.deleteAll(newDomain.getListOpdMenu());

         

            List<TbJenisSarana> listJenisSarana =  jenisSaranaRepository.findAll();
            List<TbOpdMenu> listOpdMenu = new ArrayList<>(); 
            for (String str: domain.getTempOpdMenus() ) {
                for (TbJenisSarana jenisSarana: listJenisSarana) {
                    if ( (jenisSarana.getKode1() + ". " + jenisSarana.getDescription()).equals(str) ){
                        TbOpdMenu newTbOpdMenu = new TbOpdMenu();
                        newTbOpdMenu.setJenisSaranaBean(jenisSarana);
                        newTbOpdMenu.setOpdBean(newDomain);
                        listOpdMenu.add(newTbOpdMenu);
                        break;
                    }
                }
            }
        
            newDomain.setListOpdMenu(null);
            if (opdRepository.save(newDomain) != null) {
                newDomain.setTempInt1(1); //Karena langsung edit
                try {
                    opdMenuRepository.deleteAll(domain.getListOpdMenu());    
                } catch (Exception e) {
                }
                try {
                    opdMenuRepository.saveAll(listOpdMenu);    
                } catch (Exception e) {
                }
                redirectAttributes.addFlashAttribute("saveUser", "success");
    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/dinas/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbOpd domainUpdate = opdRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());


        
            if (domainUpdate.getListOpdMenu().size()>0)
                opdMenuRepository.deleteAll(domainUpdate.getListOpdMenu());

            domainUpdate.setListOpdMenu(null);

            List<TbJenisSarana> listJenisSarana =  jenisSaranaRepository.findAll();
            List<TbOpdMenu> listOpdMenu = new ArrayList<>(); 
            for (String str: domain.getTempOpdMenus() ) {
                for (TbJenisSarana jenisSarana: listJenisSarana) {
                    if ( (jenisSarana.getKode1() + ". " + jenisSarana.getDescription()).equals(str) ){
                        TbOpdMenu newTbOpdMenu = new TbOpdMenu();
                        newTbOpdMenu.setJenisSaranaBean(jenisSarana);
                        newTbOpdMenu.setOpdBean(domainUpdate);
                        listOpdMenu.add(newTbOpdMenu);
                        break;
                    }
                }
            }


            opdRepository.save(domainUpdate);

            opdMenuRepository.saveAll(listOpdMenu);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/dinas/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/dinas/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/dinas/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/dinas/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbOpd domainToDelete = opdRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                opdRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbOpd newDomain = new TbOpd();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/dinas/dinas_form";
            }

        } else if (operation.equals("edit_form")) {

            TbOpd domain = opdRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete

                List<String> tempOpdMenus = new ArrayList<>();
                for (TbOpdMenu opdMenu: domain.getListOpdMenu()) {
                    tempOpdMenus.add(opdMenu.getJenisSaranaBean().getKode1() + ". " + opdMenu.getJenisSaranaBean().getDescription());
                }
                domain.setTempOpdMenus(tempOpdMenus);


                model.addAttribute("domain", domain);                                               
                          

                return "master/dinas/dinas_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/dinas/";
    }

    

    @RequestMapping("/master/dinas/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";

       
        return result;
    }

}
