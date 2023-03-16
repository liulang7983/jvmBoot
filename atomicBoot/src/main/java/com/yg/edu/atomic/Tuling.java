package com.yg.edu.atomic;

import lombok.Data;

@Data
public class Tuling {

    private Integer sequence;

    public Tuling(Integer seq){
        sequence = seq;
    }
}
