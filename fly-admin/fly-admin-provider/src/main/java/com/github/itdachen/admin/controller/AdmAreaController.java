package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IAdmAreaService;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行政区划代码
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
@RestController
@RequestMapping("/admin/adm/area")
@FuncTitle("行政区划代码")
public class AdmAreaController {
    private static final Logger logger = LoggerFactory.getLogger(AdmAreaController.class);

    private final IAdmAreaService admAreaService;

    public AdmAreaController(IAdmAreaService admAreaService) {
        this.admAreaService = admAreaService;
    }

}