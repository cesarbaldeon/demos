package me.challenge.bcp.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MonedaResponse implements Serializable {

    private String code;
    private String name;

}
