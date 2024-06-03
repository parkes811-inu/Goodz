package com.springproject.goodz.pay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproject.goodz.product.dto.Product;
import com.springproject.goodz.product.service.ProductService;
import com.springproject.goodz.user.dto.Shippingaddress;
import com.springproject.goodz.user.dto.Users;
import com.springproject.goodz.user.service.UserService;
import com.springproject.goodz.utils.service.FileService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    /**
     * 결제 페이지 화면 
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/buy/{p_no}")
    public String buy(@PathVariable("p_no") int pNo,
                    @RequestParam("size") String size,
                    Model model, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        List<Product> products = productService.UsedInPay(pNo);

        if (products.isEmpty()) {

            // 일단 그냥
            return "/user"; 
        }

        // 상품 정보가 비어있지 않은 경우
        Product product = products.get(0); // 여기서는 리스트의 첫 번째 상품을 가져옵니다.
        model.addAttribute("product", product); // 모델에 상품 정보를 추가합니다.

        // 기본 배송지를 찾기 위한 로직
        Shippingaddress defaultAddress = null;
        List<Shippingaddress> addresses = userService.selectByUserId(user.getUserId());
        for (Shippingaddress address : addresses) {
            if (userService.isDefaultAddress(address.getAddressNo())) {
                defaultAddress = address;
                break;
            }
        }

        // 기본 배송지가 있는지 여부를 모델에 추가
        model.addAttribute("defaultAddress", defaultAddress);
        model.addAttribute("hasAddress", !addresses.isEmpty());
        model.addAttribute("addresses", addresses);

        return "pay/buy"; // 상품 구매 페이지로 이동합니다.
    }

    @GetMapping("/success")
    public String success() {
        return "/pay/success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "/pay/fail";
    }

    @GetMapping("/complete")
    public String complete() {
        return "/pay/complete";
    }

    @GetMapping("/sell")
    public String sell() {
        return "/pay/sell";
    }

    /**
     * 성공시 그 내역들 불러오기
     * @param params
     * @param model
     * @return
     */
    @PostMapping("/success")
    public String handleSuccess(@RequestParam Map<String, String> params, Model model) {
        // 성공 처리 로직 추가
        model.addAttribute("params", params);
        return "pay/success";
    }

    /**
     * 실패시 그 내역을 불러오기
     * @param params
     * @param model
     * @return
     */
    @PostMapping("/fail")
    public String handleFail(@RequestParam Map<String, String> params, Model model) {
        // 실패 처리 로직 추가
        model.addAttribute("params", params);
        return "pay/fail";
    }
}

