package com.gov.sys.data;

public class ParamQuery implements Query {

    private static final long serialVersionUID = 1L;

    private ParamDTO paramDTO;

    public ParamQuery() {
    }

    public ParamQuery(ParamDTO paramDTO) {
        this.paramDTO = paramDTO;
    }

    public ParamDTO getParamDTO() {
        return paramDTO;
    }

    public void setParamDTO(ParamDTO paramDTO) {
        this.paramDTO = paramDTO;
    }

}