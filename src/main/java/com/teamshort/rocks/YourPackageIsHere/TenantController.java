package com.teamshort.rocks.YourPackageIsHere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.ParseException;

@Controller
public class TenantController {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @GetMapping("/tenant/{id}")
    public String getTenantPage(@PathVariable String id, Model m) {
        long ID = Long.parseLong(id);
        Tenant tenant = tenantRepository.findById(ID);
        m.addAttribute("tenant", tenant);
        return "tenant";
    }

    @GetMapping("/tenant/add")
    public String getTenantAddPage() {
        return "tenant";
    }

    @PostMapping("/tenantcreate")
    public RedirectView createUser(Principal p, String firstname, String lastname, String email, String aptnum, String phonenum) throws ParseException {
        Building building = buildingRepository.findByUsername(p.getName());
        Tenant tenant = new Tenant(firstname,lastname,email,aptnum,phonenum, building);
        tenantRepository.save(tenant);

        return new RedirectView("/");
    }

}
