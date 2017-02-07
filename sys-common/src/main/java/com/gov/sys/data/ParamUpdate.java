package com.gov.sys.data;



public class ParamUpdate implements Update{
    
    private ParamDTO paramDto;
    
    public ParamUpdate() {
        super();
    }

    public ParamUpdate(ParamDTO paramDto) {
        super();
        this.paramDto = paramDto;
    }

    public ParamDTO getParamDto() {
        return paramDto;
    }

    public void setParamDto(ParamDTO paramDto) {
        this.paramDto = paramDto;
    }

    public DTO getDTO() {
        // TODO Auto-generated method stub
        return this.paramDto;
    }

}
