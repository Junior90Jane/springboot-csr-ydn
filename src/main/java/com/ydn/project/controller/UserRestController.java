package com.ydn.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.model.dto.AdminDto;
import com.ydn.project.model.entity.Admin;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.AdminService;

import jakarta.validation.Valid;

/**
請求方法 URL 路徑               功能說明          請求參數              回應
-----------------------------------------------------------------------------------------------------
POST   /rest/user/login     使用者登入          無                成功時返回成功訊息，無回傳資料。
POST   /rest/user/logout    使用者登出          無                成功時返回成功訊息，無回傳資料。
*/

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = {"http://localhost:9090"}, allowCredentials = "true")
public class UserRestController {
	

	
}
