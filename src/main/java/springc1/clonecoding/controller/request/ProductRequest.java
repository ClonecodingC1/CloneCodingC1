package springc1.clonecoding.controller.request;


import lombok.Getter;

import java.util.List;

@Getter
public class ProductRequest {

    private String name;

    private Long price;

    private List<String> imgProductList;

    private String content;

    private String location;
}