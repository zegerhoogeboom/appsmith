package com.appsmith.server.controllers;

import com.appsmith.server.constants.Url;
import com.appsmith.server.domains.Permission;
import com.appsmith.server.services.PermissionService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.PERMISSION_URL)
@Timed
public class PermissionController extends BaseController<PermissionService, Permission, String> {

    @Autowired
    public PermissionController(PermissionService service) {
        super(service);
    }
}
