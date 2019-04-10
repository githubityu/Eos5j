package io.eblock.eos4j.api.vo.account;

/**
 * Created by dyz on 2018/11/19.
 */

public class RefundRequestBean {
    private String owner;//账号
    private String request_time;//赎回请求时间
    private String net_amount;//正在赎回net的eos数量
    private String cpu_amount;//正在赎回cpu的eos数量

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public String getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(String net_amount) {
        this.net_amount = net_amount;
    }

    public String getCpu_amount() {
        return cpu_amount;
    }

    public void setCpu_amount(String cpu_amount) {
        this.cpu_amount = cpu_amount;
    }
}
