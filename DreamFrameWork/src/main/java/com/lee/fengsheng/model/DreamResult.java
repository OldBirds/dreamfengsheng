package com.lee.fengsheng.model;

/**
 * Created by XinSheng on 2016/12/15.
 */
public class DreamResult<D> {
    public boolean result;
    public String msg;
    public  D data;
    private int errcode;
    private String errmsg;
    private Object p2pdata;

    public DreamResult(boolean result,String msg,D data){
        this.result=result;
        this.msg=msg;
        this.data=data;
    }

    public static <D> DreamResult<D>  instance(boolean result,String msg,D data){
        return new DreamResult<D>(result,msg,data);
    }

    public DreamResult(int ErrCode, String ErrMsg, Object P2pData)
    {
        this.errcode = ErrCode;
        this.errmsg = ErrMsg;
        this.p2pdata = P2pData;
    }
    public int getErrcode() {
        return errcode;
    }
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public Object getP2pdata() {
        return p2pdata;
    }
    public void setP2pdata(Object p2pdata) {
        this.p2pdata = p2pdata;
    }
}
