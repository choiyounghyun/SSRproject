package com.example.frontPJ.controller;

import com.example.frontPJ.dto.ItemDto;
import com.example.frontPJ.entity.Item;
import com.example.frontPJ.repository.ItemRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemRepo itemRepo;

    @GetMapping("/")
    public String list(Model model) throws IOException {
        List<ItemDto> list = new ArrayList<>();
        //h2 DB에 저장되어 있는 모든 상품 정보 productList에 저장
        List<Item> productList = itemRepo.findAll();

        for(Item item : productList){
            ItemDto itemDto = new ItemDto();
            itemDto.setProductName(item.getProductName());
            itemDto.setDescription(item.getDescription());
            itemDto.setPrice(item.getPrice());
            itemDto.setDc(item.getDc());
            itemDto.setProductType(item.getProductType());
            itemDto.setBestItem(item.getBestItem());
            list.add(itemDto);
        }
        model.addAttribute("itemList", list);
        return "index";
    }

    
    @PostMapping("/")
    public ResponseEntity products(@RequestBody ArrayList<ItemDto> list, HttpSession httpSession){
        ArrayList<String> itemlist = new ArrayList<>();

        System.out.println(list.size());
        for(ItemDto item : list){
            String temp = item.getImage();
            String pro = temp.substring(temp.lastIndexOf("/")+1,temp.lastIndexOf("."));

            itemlist.add(pro);
        }

        httpSession.setAttribute("data", itemlist);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/products")
    public String home(HttpSession httpSession, Model model) {
        ArrayList<String> itemList = (ArrayList<String>) httpSession.getAttribute("data");

        List<ItemDto> list = new ArrayList<>();
        List<Item> lists = new ArrayList<>();


        for(int i=0; i<itemList.size(); i++){
            lists = itemRepo.findByImage(itemList.get(i));

            ItemDto itemDto = new ItemDto();
            itemDto.setProductName(lists.get(0).getProductName());
            itemDto.setPrice(lists.get(0).getPrice());
            itemDto.setDc(lists.get(0).getDc());
            itemDto.setImage(lists.get(0).getImage());
            if(lists.get(0).getPrice()*(100-lists.get(0).getDc())/100%10==0){
                itemDto.setDcPrice(Double.valueOf(lists.get(0).getPrice() *
                        (100 - lists.get(0).getDc()) * 0.01).intValue());
            }else{
                itemDto.setDcPrice(Double.valueOf(lists.get(0).getPrice() * (100 - lists.get(0).getDc()) *
                        0.01 -lists.get(0).getPrice() * (100 - lists.get(0).getDc()) * 0.01%10 ).intValue());
            }
            list.add(itemDto);
        }
        model.addAttribute("productList", list);
        return "products";
    }
}
