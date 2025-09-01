package com.Uni.UNI_sys.domainServandCont;

import com.Uni.UNI_sys.domain.Roles;
import com.Uni.UNI_sys.domain.Admin;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class adminController {
    private final AdminServ adminServ;


    @GetMapping("/alladmins")
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok().body(adminServ.getAllAdmins());
    }

    @PostMapping("/save/admins")
    public ResponseEntity<Admin> saveAdmins(@RequestBody Admin admin) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admins/save/admins")
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(adminServ.saveAdmin(admin));
    }

    @PostMapping("/save/roles")
    public ResponseEntity<Roles> saveRoles(@RequestBody Roles roles) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admins/save/roles")
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(adminServ.saveRole(roles));
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RolestoUser dto) {
        adminServ.addRoleToAdmin(dto.getUsername(), dto.getRolename());
        return ResponseEntity.ok().build();
    }
}

@Data
class RolestoUser {
    private String username;
    private String rolename;
}
