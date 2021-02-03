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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.desgreen.gov.database.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.jpa_repository.TbKecamatanRepository;
import com.desgreen.gov.database.jpa_repository.TbOpdRepository;
import com.desgreen.gov.database.jpa_repository.UserRolesRepository;
import com.desgreen.gov.database.jpa_repository.UsersRepository;
import com.desgreen.gov.database.model.FUser;
import com.desgreen.gov.database.model.FUserRoles;
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
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private TbOpdRepository opdRepository;
    
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private SecurityUtils securityUtils;


    @ModelAttribute("allStringRoles")
    public List<String> getAllOpdMenus() {
        List<String> list = new ArrayList<>();
        for (String myRoleID: Role.getAuthRoles() ) {
            list.add(myRoleID); 
        }        
        return list;
    }

    @ModelAttribute("allListOpds")
    public List<TbOpd> getListOpds() {
        return opdRepository.findAll();
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/admin/users")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  

        final FUser newDomain =new FUser();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", usersRepository.findAll());

        logger.info("# Form Task");
      
        return "users/user_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/admin/users_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final FUser domain, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            FUser newDomain = new FUser();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            // domainUpdate.setUsername("bagus");
            newDomain.setUsername(domain.getUsername());
            // domainUpdate.setEmail("bagus.stimata@gmail.com emailnya");
            newDomain.setEmail(domain.getEmail());
            newDomain.setLocked(domain.isLocked());

            newDomain.setFullName(domain.getFullName());
            newDomain.setPhone(domain.getPhone());
            newDomain.setNotes(domain.getNotes());

            newDomain.setOpdBean(domain.getOpdBean());

            if (domain.getPassword().equals("")) domain.setPassword("Welcome1"); 
            newDomain.setPassword(PassEncoding.getInstance().passwordEncoder.encode(domain.getPassword().trim() ));

            //Sementara otomatis
            List<FUserRoles> listFUserLRoles = new ArrayList<>();
            FUserRoles userRole1 = new FUserRoles();
            userRole1.setFuserBean(newDomain);
            userRole1.setRoleID(Role.USER);
            FUserRoles userRole2 = new FUserRoles();
            userRole2.setFuserBean(newDomain);
            userRole2.setRoleID(Role.ADMIN);
    
            listFUserLRoles.add(userRole1);
            listFUserLRoles.add(userRole2);
    
            newDomain.setFuserRoles(listFUserLRoles);

            //New Tidak memerlukan
            // if (newDomain.getFuserRoles().size()>0)
            // userRolesRepository.deleteAll(newDomain.getFuserRoles());

            newDomain.setFuserRoles(null);
        
            List<FUserRoles> listUserRoles = new ArrayList<>(); 
            for (String str: domain.getTempRoles()) {
                for (String roleID: Role.getAuthRoles()) {
                    if ( roleID.equals(str) ){
                        FUserRoles newUserRole = new FUserRoles();
                        newUserRole.setRoleID(roleID);
                        newUserRole.setFuserBean(newDomain);
                        listUserRoles.add(newUserRole);
                        break;
                    }
                }
            }


            if (usersRepository.save(newDomain) != null) {
                
                if (userRolesRepository.save(userRole1) !=null &&  
                    userRolesRepository.save(userRole2) !=null) {
                        newDomain.setTempInt1(1);

                        userRolesRepository.saveAll(listUserRoles);
                        redirectAttributes.addFlashAttribute("saveUser", "success");
                }
    
    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/admin/users/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            FUser domainUpdate = usersRepository.findById(domain.getId() );
            // if (domainUpdate !=null) {
                domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete
                domainUpdate.setTempInt1(1);

                // domainUpdate.setUsername("bagus");
                domainUpdate.setUsername(domain.getUsername());
                // domainUpdate.setEmail("bagus.stimata@gmail.com emailnya");
                domainUpdate.setEmail(domain.getEmail());
                domainUpdate.setNotes(domain.getNotes());

                domainUpdate.setLocked(domain.isLocked());

                domainUpdate.setOpdBean(domain.getOpdBean());

                if (domain.isTempBol2()==true ) {
                    if (domain.getPassword().equals("")) domain.setPassword("Welcome1"); 
                    // domainUpdate.setNotes("#!*(%$" +domain.getPassword().trim() + "12345xzy");
                    domainUpdate.setPassword(PassEncoding.getInstance().passwordEncoder.encode(domain.getPassword().trim() ));
                }

                domainUpdate.setFullName(domain.getFullName());
                domainUpdate.setPhone(domain.getPhone());

               
                if (domainUpdate.getFuserRoles().size()>0)
                    userRolesRepository.deleteAll(domainUpdate.getFuserRoles());

                domainUpdate.setFuserRoles(null);
              
                List<FUserRoles> listUserRoles = new ArrayList<>(); 
                for (String str: domain.getTempRoles()) {
                    for (String roleID: Role.getAuthRoles()) {
                        if ( roleID.equals(str) ){
                            FUserRoles newUserRole = new FUserRoles();
                            newUserRole.setRoleID(roleID);
                            newUserRole.setFuserBean(domainUpdate);
                            listUserRoles.add(newUserRole);
                            break;
                        }
                    }
                }



                usersRepository.save(domainUpdate);

                userRolesRepository.saveAll(listUserRoles);
              
                redirectAttributes.addFlashAttribute("saveUser", "success");

                return "redirect:/admin/users/edit_form/" + domain.getId();
                // return "redirect:/" ;
            // }
        }
       
        
        return "redirect:/admin/users";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/admin/users/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/users/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                FUser domainToDelete = usersRepository.findById(id);        
                userRolesRepository.deleteAll(domainToDelete.getFuserRoles());
                usersRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            FUser newDomain = new FUser();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);         
                       
                return "users/user_form";
            }

        } else if (operation.equals("edit_form")) {
            FUser domain = usersRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete



                List<String> tempRoles = new ArrayList<>();
                for (FUserRoles userRoleMenu: domain.getFuserRoles() ) {
                    tempRoles.add(userRoleMenu.getRoleID());
                }
                domain.setTempRoles(tempRoles);


                model.addAttribute("domain", domain);                


                return "users/user_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
       
        }

        return "redirect:/admin/users";
    }


}
